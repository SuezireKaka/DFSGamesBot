package www.disbot.dfsGames.bot.parser.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.DiscordParser;
import www.disbot.dfsGames.game.model.GameFlowVO;
import www.disbot.dfsGames.game.model.GameVO;
import www.disbot.dfsGames.game.model.LaunchVO;

@AllArgsConstructor
public class LaunchParser extends DiscordParser {
	private LaunchVO vo;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();
		
		String launchKeyString = LaunchVO.LAUNCH_TITLE;
		Pair<ParseType, String> launchKey = new Pair<>(ParseType.KEY, launchKeyString);
		
		String launchValString = LaunchVO.LAUNCH_MESSAGE.formatted(
				vo.getGame().getName());
		Pair<ParseType, String> launchVal = new Pair<>(ParseType.VAL, launchValString);

		String messageKeyString = CurrentUserStatusVO.MESSAGE_TITLE;
		Pair<ParseType, String> messageKey = new Pair<>(ParseType.KEY, messageKeyString);
		
		String messageValString = vo.getMessage();
		Pair<ParseType, String> messageVal = new Pair<>(ParseType.VAL, messageValString);
		
		String statusKeyString = CurrentUserStatusVO.STATUS_TITLE;
		Pair<ParseType, String> statusKey = new Pair<>(ParseType.KEY, statusKeyString);
		
		String statusValString = CurrentUserStatusVO.STATUS_FORMAT.formatted(
				vo.getNowNumber(), vo.getRequiredNumber());
		Pair<ParseType, String> statusVal = new Pair<>(ParseType.VAL, statusValString);
		
		lemma.add(launchKey);
		lemma.add(launchVal);
		lemma.add(messageKey);
		lemma.add(messageVal);
		lemma.add(statusKey);
		lemma.add(statusVal);
		
		if (vo.getGame().isStarted()) {
			String orderKeyString = GameFlowVO.PLAYER_ORDER_TITLE;
			Pair<ParseType, String> orderKey = new Pair<>(ParseType.KEY, orderKeyString);
			
			String orderValString = vo.getManager().getPlayerOrder();
			Pair<ParseType, String> orderVal = new Pair<>(ParseType.VAL, orderValString);
			
			String turnKeyString = GameFlowVO.TURN_TITLE;
			Pair<ParseType, String> turnKey = new Pair<>(ParseType.KEY, turnKeyString);
			
			String turnValString = GameFlowVO.TURN_FORMAT.formatted(
					vo.getManager().getPlayerList().get(0).getAsMention());
			Pair<ParseType, String> turnVal = new Pair<>(ParseType.VAL, turnValString);
			
			lemma.add(orderKey);
			lemma.add(orderVal);
			lemma.add(turnKey);
			lemma.add(turnVal);
		}

		return lemma;
	}
	
	@Override
	public File extractFile() {
		GameVO game = vo.getGame();
		
		return game != null && game.isStarted()
				? game.getGameFile()
				:null;
	}
}
