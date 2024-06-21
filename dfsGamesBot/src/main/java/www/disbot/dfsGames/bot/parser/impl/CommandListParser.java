package www.disbot.dfsGames.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.dfsGames.bot.model.data.CommandVO;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.DiscordParser;

@AllArgsConstructor
public class CommandListParser extends DiscordParser {
	private List<CommandVO> commandList = new ArrayList<>();

	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		List<Pair<ParseType, String>> lemma = new ArrayList<>();
		
		for (CommandVO cmd : commandList) {
			String keyString = cmd.getCommand();
			Pair<ParseType, String> key = new Pair<>(ParseType.KEY, keyString);
			
			String valString = cmd.getExplain();
			Pair<ParseType, String> val = new Pair<>(ParseType.VAL, valString);
			
			lemma.add(key);
			lemma.add(val);
			
			
		}
		
		return lemma;
	}
}
