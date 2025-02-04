metadata {
	definition (name: "Russound Zone New", namespace: "redloro-smartthings", author: "pkalsi@gmail.com"){
    
	capability "Audio Volume"   
	capability "Switch"
    capability "Media Input Source"
    capability "Switch Level"

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
    
	}
   
   simulator {
		// status messages
		status "on": "switch:on"
		status "off": "switch:off"

		// reply messages
		reply "on": "switch:on"
		reply "off": "switch:off"
	}

  tiles(scale: 2) {
    multiAttributeTile(name:"state", type:"generic", width:6, height:4) {
      tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
        attributeState "on", label:'On', action:"switch.off", icon:"st.Electronics.electronics16", backgroundColor:"#79b821", nextState:"off"
        attributeState "off", label:'Off', action:"switch.on", icon:"st.Electronics.electronics16", backgroundColor:"#ffffff", nextState:"on"
      }
      tileAttribute ("source", key: "SECONDARY_CONTROL") {
        attributeState "source", label:'${currentValue}'
      }
    }

    // Row 1
    controlTile("volume", "device.volume", "slider", height: 1, width: 6, range:"(0..100)") {
      state "volume", label: "Volume", action:"music Player.setLevel", backgroundColor:"#00a0dc"
    }

    // Row 2-3
    standardTile("0", "device.source0", decoration: "flat", width: 2, height: 2) {
      state("off", label:"Source 1", action:"source0", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-gray.png", backgroundColor:"#ffffff")
      state("on", label:"Source 1", action:"source0", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-green.png", backgroundColor:"#ffffff")
    }
    standardTile("1", "device.source1", decoration: "flat", width: 2, height: 2) {
      state("off", label:"Source 2", action:"source1", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-gray.png", backgroundColor:"#ffffff")
      state("on", label:"Source 2", action:"source1", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-green.png", backgroundColor:"#ffffff")
    }
    standardTile("2", "device.source2", decoration: "flat", width: 2, height: 2) {
      state("off", label:"Source 3", action:"source2", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-gray.png", backgroundColor:"#ffffff")
      state("on", label:"Source 3", action:"source2", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-green.png", backgroundColor:"#ffffff")
    }
    standardTile("3", "device.source3", decoration: "flat", width: 2, height: 2) {
      state("off", label:"Source 4", action:"source3", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-gray.png", backgroundColor:"#ffffff")
      state("on", label:"Source 4", action:"source3", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-green.png", backgroundColor:"#ffffff")
    }
    standardTile("4", "device.source4", decoration: "flat", width: 2, height: 2) {
      state("off", label:"Source 5", action:"source4", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-gray.png", backgroundColor:"#ffffff")
      state("on", label:"Source 5", action:"source4", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-green.png", backgroundColor:"#ffffff")
    }
    standardTile("5", "device.source5", decoration: "flat", width: 2, height: 2) {
      state("off", label:"Source 6", action:"source5", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-gray.png", backgroundColor:"#ffffff")
      state("on", label:"Source 6", action:"source5", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-green.png", backgroundColor:"#ffffff")
    }


    // Row 4
    standardTile("loudness", "device.loudness", decoration: "flat", width: 2, height: 2) {
      state "off", label:'Loudness', action:"loudnessOn", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-gray.png", backgroundColor:"#ffffff"
      state "on", label:'Loudness', action:"loudnessOff", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-loudness.png", backgroundColor:"#ffffff"
    }
    standardTile("partyMode", "device.partyMode", decoration: "flat", width: 2, height: 2, inactiveLabel: false) {
      state "off", label:'Party Mode', action:"partyModeOn", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-gray.png", backgroundColor:"#ffffff"
      state "on", label:'Party Mode', action:"partyModeOff", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-party.png", backgroundColor:"#ffffff"
    }
    standardTile("alloff", "device.status", decoration: "flat", width: 2, height: 2, inactiveLabel: false) {
      state "default", label:"All Off", action:"allOff", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/indicator-dot-power.png", backgroundColor:"#ffffff"
    }

    // Row 5-6
    standardTile("bassLevelLabel", "default", decoration: "flat", height: 1, width: 1) {
      state "default", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/bass.png"
    }
    controlTile("bassLevel", "device.bassLevel", "slider", height: 1, width: 3, range:"(-10..10)") {
      state "default", action:"bassLevel", backgroundColor:"#00a0dc"
    }
    standardTile("trebleLevelLabel", "default", decoration: "flat", height: 1, width: 1) {
      state "default", icon:"https://raw.githubusercontent.com/redloro/smartthings/master/images/treble.png"
    }
    controlTile("trebleLevel", "device.trebleLevel", "slider", height: 1, width: 3, range:"(-10..10)") {
      state "default", action:"trebleLevel", backgroundColor:"#00a0dc"
    }

    standardTile("refresh", "device.status", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
      state "default", label:"Refresh", action:"refresh.refresh", icon:"st.secondary.refresh-icon", backgroundColor:"#ffffff"
    }

    // Defines which tile to show in the overview
    main "state"

    // Defines which tile(s) to show when user opens the detailed view
    details([
      "state",
      "volume",
      "0","1","2","3",
      "loudness", "partyMode", "alloff",
      "bassLevelLabel", "bassLevel",
      "refresh",
      "trebleLevelLabel", "trebleLevel"
    ])
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

def setVolume(value) { sendCommand(["volume": (value/2).intValue()], true) }

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
    
def setLevel(value) { 

	switch(value) {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        sendCommand(["source": value-1], true);
        break;
       
        default:
        sendCommand(["source": 0], true);
        break;
        }
    }

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