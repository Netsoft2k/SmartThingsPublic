metadata {
	definition (name: "Russound Zone New", namespace: "redloro-smartthings", author: "pkalsi@gmail.com"){
    
	capability "Audio Volume"   
	capability "Switch"
    capability "Media Input Source"
    capability "Switch Level"

	/*
    command "source0"
    command "source1"
    command "source2"
    command "source3"
    command "source4"
    command "source5"
    command "loudnessOn"
    command "loudnessOff"
    command "bassLevel"
    command "trebleLevel"
    command "partyModeOn"
    command "partyModeOff"
    command "allOff"
    command "zone"
    */
    
	}
   
   simulator {
		// status messages
		status "on": "switch:on"
		status "off": "switch:off"

		// reply messages
		reply "on": "switch:on"
		reply "off": "switch:off"
	}

	tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			state "on", label: '${name}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#00A0DC"
		}
        
		main "switch"
		details "switch"
	}
        
}

def on() { sendCommand(["state": 1], false) }
def off() { sendCommand(["state": 0], false) }
def source0() { sendCommand(["source": 0], true) }
def source1() { sendCommand(["source": 1], true) }
def source2() { sendCommand(["source": 2], true) }
def source3() { sendCommand(["source": 3], true) }
def source4() { sendCommand(["source": 4], true) }
def source5() { sendCommand(["source": 5], true) }

def setVolume(value) { sendCommand(["volume": (value).intValue()], true) }

def setInputSource(value) { 

	switch(value) {
        case "AM":
        sendCommand(["source": 1], true);
        break;

        case "USB":
        sendCommand(["source": 5], true);
        break;
        
        default:
        sendCommand(["source": 0], true);
        break;
        }
    }
    
def setLevel(value) { sendCommand(["source": value], true) }

def loudnessOn() { sendCommand(["loudness": 1], false) }
def loudnessOff() { sendCommand(["loudness": 0], false) }
def partyModeOn() { parent.partyMode(["state": 1, "master": getZone(), "source": getSource(), "volume": getVolume()]) }
def partyModeOff() { partyMode(["state": 0]) }
def bassLevel(value) { sendCommand(["bass": value+10], false) }
def trebleLevel(value) { sendCommand(["treble": value+10], false) }
def allOff() { sendCommand(["all": 0], false) }
def refresh() { sendCommand([], false) }

def poll() {
  refresh()
}

def zone(evt) {
  //log.debug "ZONE${getZone()} zone(${evt})"

  /*
  * Zone On/Off state (0x00 = OFF or 0x01 = ON)
  */
  if (evt.containsKey("state")) {
    //log.debug "setting state to ${result.state}"
    sendEvent(name: "switch", value: (evt.state == 1) ? "on" : "off")

    //turn off party mode
    if (evt.state == 0 || !device.currentState("partyMode")) {
      partyMode(["state": 0])
    }
  }

  /*
  * Zone Volume level (0x00 - 0x32, 0x00 = 0 ... 0x32 = 100 Displayed)
  */
  if (evt.containsKey("volume")) {
    //log.debug "setting volume to ${result.volume * 2}"
    sendEvent(name: "volume", value: evt.volume)
  }

  /*
  * Zone Loudness (0x00 = OFF, 0x01 = ON )
  */
  if (evt.containsKey("loudness")) {
    //log.debug "setting loudness to ${result.loudness}"
    sendEvent(name: "loudness", value: (evt.loudness == 1) ? "on" : "off")
  }

  /*
  * Zone Bass level (0x00 = -10 ... 0x0A = Flat ... 0x14 = +10)
  */
  if (evt.containsKey("bass")) {
    //log.debug "setting bass to ${result.bass - 10}"
    sendEvent(name: "bassLevel", value: evt.bass - 10)
  }

  /*
  * Zone Treble level (0x00 = -10 ... 0x0A = Flat ... 0x14 = +10)
  */
  if (evt.containsKey("treble")) {
    //log.debug "setting treble to ${result.treble - 10}"
    sendEvent(name: "trebleLevel", value: evt.treble - 10)
  }

  /*
  * Zone Source selected (0-5)
  */
  if (evt.containsKey("source")) {
    //log.debug "setting source to ${result.source}"
    for (def i = 0; i < 6; i++) {
      if (i == evt.source) {
        state.source = i
        sendEvent(name: "source${i}", value: "on")
        sendEvent(name: "source", value: "Source ${i+1}: ${evt.sourceName}")
      }
      else {
        sendEvent(name: "source${i}", value: "off")
      }
    }
  }
}

def partyMode(evt) {
  // ["state": "", "master": "", "source": "", "volume": ""]
  //log.debug "ZONE${getZone()} partyMode(${evt})"
  if (evt.containsKey("state")) {
    sendEvent(name: "partyMode", value: (evt.state == 1) ? "on" : "off")
    if (evt.state == 1) {
      sendCommand(["state": 1], false)
    }
  } else {
    // exit if partyMode is off
    if (getPartyMode() == 0) {
      return
    }
  }

  if (evt.containsKey("volume")) {
    sendCommand(["volume": evt.volume], false)
  }

  if (evt.containsKey("source")) {
    sendCommand(["source": evt.source], false)
  }
}

private sendCommand(evt, broadcast) {
  //log.debug "ZONE${getZone()} sendCommand(${evt}, ${broadcast})"

  // send command to partyMode
  if (broadcast && getPartyMode()) {
    parent.partyMode(evt)
    return
  }

  // send command to Russound
  def part = ""
  if (evt.size() == 1) {
    part = "/${evt.keySet()[0]}/${evt.values()[0]}"
  }

  //log.debug "ZONE${getZone()} calling parent.sendCommand"
  parent.sendCommand("/plugins/rnet/controllers/${getController()}/zones/${getZone()}${part}")
}

private getTrebelLevel() {
  return device.currentState("trebleLevel").getValue().toInteger()
}

private getBassLevel() {
  return device.currentState("bassLevel").getValue().toInteger()
}

private getPartyMode() {
  return (device.currentState("partyMode").getValue() == "on") ? 1 : 0;
}

private getVolume() {
  return (device.currentState("volume").getValue().toInteger())
}

private getSource() {
    for (def i = 0; i < 6; i++) {
      if (device.currentState("source${i}").getValue()  == "on") {
        return i
      }
    }
}

private getController() {
  return new String(device.deviceNetworkId).tokenize('|')[1]
}

private getZone() {
  return new String(device.deviceNetworkId).tokenize('|')[2]
}