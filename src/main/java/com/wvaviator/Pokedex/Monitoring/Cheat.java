package com.wvaviator.Pokedex.Monitoring;

import java.sql.Timestamp;

public class Cheat {
	
	private CheatType cheatType;
	private String uuid;
	private Timestamp time;
	
	public Cheat(String uuid, CheatType cheatType) {
		
		this.cheatType = cheatType;
		this.uuid = uuid;
		this.time = new Timestamp(System.currentTimeMillis());
		
	}
	
	public CheatType getCheatType() {
		return this.cheatType;
	}
	
	public String getStringCheatType() {
		return this.cheatType.toString();
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public Timestamp getTime() {
		return this.time;
	}
	
	public long timeSince(Timestamp t) {
		long cheatTime = this.time.getTime();
		long lastCheat = t.getTime();
		long timeSince = cheatTime - lastCheat;
		return timeSince;
	}

}
