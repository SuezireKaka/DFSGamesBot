package www.disbot.dfsGames.bot.parser.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.DiscordParser;
import www.disbot.dfsGames.game.model.MetaInfoVO;

@AllArgsConstructor
public class GameInfoParser extends DiscordParser {
	private MetaInfoVO vo;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();
		
		String directedKeyString = vo.getClass().getDeclaredFields()[1].getName();
		Pair<ParseType, String> directedKey = new Pair<>(ParseType.KEY, directedKeyString);
		
		String directedValString = String.valueOf(vo.isDirected());
		Pair<ParseType, String> directedVal = new Pair<>(ParseType.VAL, directedValString);

		String nameKeyString = vo.getClass().getDeclaredFields()[2].getName();
		Pair<ParseType, String> nameKey = new Pair<>(ParseType.KEY, nameKeyString);
		
		String nameValString = vo.getName();
		Pair<ParseType, String> nameVal = new Pair<>(ParseType.VAL, nameValString);
		
		String introduceKeyString = vo.getClass().getDeclaredFields()[3].getName();
		Pair<ParseType, String> introduceKey = new Pair<>(ParseType.KEY, introduceKeyString);
		
		String introduceValString = vo.getIntroduce();
		Pair<ParseType, String> introduceVal = new Pair<>(ParseType.VAL, introduceValString);
		
		lemma.add(directedKey);
		lemma.add(directedVal);
		lemma.add(nameKey);
		lemma.add(nameVal);
		lemma.add(introduceKey);
		lemma.add(introduceVal);

		return lemma;
	}

	@Override
	public File extractFile() {
		return vo.getGameFile();
	}
}
