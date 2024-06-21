package www.disbot.dfsGames.bot.util.iterateString;

import java.util.ArrayList;
import java.util.List;

public class ByOneLetterIterateStrategy implements IterateStrategy {
	@Override
	public List<String> parseToIterate(String fullContents) {
		List<String> result = new ArrayList<>();
		
		int fullLength = fullContents.length();
		
		for (int i = 0; i < fullLength; i++) {
			result.add(fullContents.substring(i, i + 1));
		}
		
		return result;
	}

}
