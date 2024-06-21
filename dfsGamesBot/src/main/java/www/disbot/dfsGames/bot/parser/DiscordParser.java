package www.disbot.dfsGames.bot.parser;

import java.util.ArrayList;
import java.util.List;

import www.disbot.dfsGames.bot.exception.TooLongKeyStateException;
import www.disbot.dfsGames.bot.model.structure.Pair;

public abstract class DiscordParser implements ContentsParser {
	@Override
	public List<List<Pair<ParseType, String>>> parse() throws Exception {
		return applyDiscordRule(parseLemma());
	}
	
	private List<List<Pair<ParseType, String>>> applyDiscordRule(
			List<Pair<ParseType, String>> lemma) 
					throws Exception {
		
		List<List<Pair<ParseType, String>>> result = new ArrayList<>();

		List<Pair<ParseType, String>> cash = new ArrayList<>();
		
		String currentState = "";
		
		for (Pair<ParseType, String> elem : lemma) {
			String realContents = elem.getSecond();
			
			if (elem.getFirst() == ParseType.KEY) {
				if (realContents.length() > MAX_ONE_VALUE_LENGTH) {
					throw new TooLongKeyStateException(realContents.length());
				}
				
				currentState += realContents;
				
				if (currentState.length() > MAX_ONE_VALUE_LENGTH) {
					result.add(cash);
					currentState = "";
				}
				else {
					currentState += LIST_SEPERATOR;
				}
				
				cash.add(elem);
			}
			
			if (elem.getFirst() == ParseType.VAL) {
				int currentLength = currentState.length();
				
				currentState += realContents;
				
				while (true) {
					if (currentState.length() > MAX_ONE_VALUE_LENGTH) {
						int pos = MAX_ONE_VALUE_LENGTH - currentLength;
						
						String earlier = realContents.substring(0, pos) + OMIT_SUFFIX;
						
						elem.setSecond(earlier);
						cash.add(elem);
						result.add(cash);
						cash.clear();
						
						realContents = OMIT_PREFIX + realContents.substring(pos);
						currentState = realContents + LIST_SEPERATOR;
					}
					else {
						currentState += LIST_SEPERATOR;
						cash.add(elem);
						break;
					}
				}
			}
		}
		
		result.add(cash);
		
		return result;
	}

}
