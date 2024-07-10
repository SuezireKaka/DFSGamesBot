package www.disbot.dfsGames.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.DiscordParser;
import www.disbot.dfsGames.game.model.GameFlowVO;
import www.disbot.dfsGames.game.model.LaunchVO;

public class GameFlowParser extends DiscordParser {
	private GameFlowVO vo;
	
	public GameFlowParser(LaunchVO launch) {
		this.vo = new GameFlowVO(launch);
	}
	
	public GameFlowParser(GameFlowVO flow) {
		this.vo = flow;
	}
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		List<Pair<ParseType, String>> lemma = new ArrayList<>();
		
		String orderKeyString = GameFlowVO.PLAYER_ORDER_TITLE;
		Pair<ParseType, String> orderKey = new Pair<>(ParseType.KEY, orderKeyString);
		
		String orderValString = vo.getOrder();
		Pair<ParseType, String> orderVal = new Pair<>(ParseType.VAL, orderValString);
		
		String turnKeyString = GameFlowVO.TURN_TITLE;
		Pair<ParseType, String> turnKey = new Pair<>(ParseType.KEY, turnKeyString);
		
		String turnValString = GameFlowVO.TURN_FORMAT
				.formatted(vo.getNowTurn());
		Pair<ParseType, String> turnVal = new Pair<>(ParseType.VAL, turnValString);
		
		lemma.add(orderKey);
		lemma.add(orderVal);
		lemma.add(turnKey);
		lemma.add(turnVal);

		return lemma;
		
	}
}
