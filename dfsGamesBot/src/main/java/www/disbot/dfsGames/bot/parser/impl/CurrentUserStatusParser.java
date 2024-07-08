package www.disbot.dfsGames.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.DiscordParser;

@AllArgsConstructor
public class CurrentUserStatusParser extends DiscordParser {
	private CurrentUserStatusVO vo;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();
		
		String messageKeyString = CurrentUserStatusVO.MESSAGE_TITLE;
		Pair<ParseType, String> messageKey = new Pair<>(ParseType.KEY, messageKeyString);
		
		String messageValString = vo.getMessage();
		Pair<ParseType, String> messageVal = new Pair<>(ParseType.VAL, messageValString);

		String statusKeyString = CurrentUserStatusVO.STATUS_TITLE;
		Pair<ParseType, String> statusKey = new Pair<>(ParseType.KEY, statusKeyString);
		
		String statusValString = CurrentUserStatusVO.STATUS_FORMAT
				.formatted(vo.getNowNumber(), vo.getRequiredNumber());
		Pair<ParseType, String> statusVal = new Pair<>(ParseType.VAL, statusValString);
		
		lemma.add(messageKey);
		lemma.add(messageVal);
		lemma.add(statusKey);
		lemma.add(statusVal);

		return lemma;
	}
}
