package www.disbot.dfsGames.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.DiscordParser;

@AllArgsConstructor
public class ArgRequireParser extends DiscordParser {
	private String require;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();
		
		Pair<ParseType, String> key = new Pair<>(ParseType.KEY, "require message");

		Pair<ParseType, String> val = new Pair<>(ParseType.VAL, require);
		
		lemma.add(key);
		lemma.add(val);

		return lemma;
	}
}
