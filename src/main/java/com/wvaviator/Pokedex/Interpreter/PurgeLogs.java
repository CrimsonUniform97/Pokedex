package com.wvaviator.Pokedex.Interpreter;

import java.sql.SQLException;

import com.wvaviator.Pokedex.Logging.PokedexQuery;
import com.wvaviator.Pokedex.Users.Chat;

public class PurgeLogs {

	public static void purgeData(PokedexQuery pdq) {

		try {
			DataRetrieval.deleteResults(pdq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Chat.toChat(pdq.getSender(), Chat.deleting);
		
	}

}
