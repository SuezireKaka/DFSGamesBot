package www.disbot.dfsGames.bot.command.api.idChecker;

import java.util.Arrays;
import java.util.List;

import www.disbot.dfsGames.api.framework.model.Entity;
import www.disbot.dfsGames.bot.util.iterateString.ByOneLetterIterateStrategy;
import www.disbot.dfsGames.bot.util.iterateString.IterateString;

public abstract class IdChacker {
	public static final List<String> ID_PIECES_LIST = Arrays.asList(new String[] {
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z"
	});
	
	public static boolean isValidId(String id) {
		return isSuitableLength(id)
				&& isComposedWell(id);
	}
	
	private static boolean isSuitableLength(String id) {
		return id.length() == Entity.DEFAULT_ID_LENGTH;
	}

	private static boolean isComposedWell(String id) {
		boolean result = true;
		
		IterateString iterId = new IterateString(id, new ByOneLetterIterateStrategy());
		
		for (String s : iterId) {
			result &= ID_PIECES_LIST.contains(s);
			
			if (! result) {
				break;
			}
		}
		
		return result;
	}
}
