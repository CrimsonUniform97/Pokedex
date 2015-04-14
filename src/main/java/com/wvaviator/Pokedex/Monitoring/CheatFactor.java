package com.wvaviator.Pokedex.Monitoring;

import java.sql.SQLException;

import com.wvaviator.Pokedex.Users.PlayerCheats;

public class CheatFactor {
	
	private int cf;
	private String uuid;
	
	public CheatFactor(String uuid) {
		
		this.uuid = uuid;
		
		try {
			this.cf = PlayerCheats.getCFFromUUID(uuid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addToCF(int amountToAdd) {
		int newAmount = this.cf + amountToAdd;
		
		try {
			PlayerCheats.updateCF(this.uuid, newAmount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.cf = newAmount;
	}
	
	public int getCF() {
		return this.cf;
	}

}
