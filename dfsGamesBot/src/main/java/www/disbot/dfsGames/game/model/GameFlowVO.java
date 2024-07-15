package www.disbot.dfsGames.game.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import www.disbot.dfsGames.bot.command.impl.MoveCommand;
import www.disbot.dfsGames.bot.parser.ContentsParser;
import www.disbot.dfsGames.game.player.PlayerManager;
import www.disbot.dfsGames.game.visitor.Visitor;

@Getter
@AllArgsConstructor
public class GameFlowVO {
	public static final String ACTION_TITLE = "action";
	
	public static final String PLAYER_ORDER_TITLE = "players";
	
	public static final String POSITION_TITLE = "position";
	
	public static final String TURN_TITLE = "now turn";
	public static final String TURN_FORMAT = "%s님의 차례입니다.";
	
	public static final String DEFAULT_ACTION = "\"%s\" 명령어로 %s 위치로 이동해주세요.";
	
	public static final String ANY_YOU_WANT = "원하시는";
	
	private String order;
	private String nowTurn;
	
	private String position = null;
	
	private String action;

	public GameFlowVO(LaunchVO launch) {
		PlayerManager manager = launch.getManager();
		
		this.order = manager.getPlayerOrder();
		this.nowTurn = manager.nowTurn().getAsMention();
		
		this.action = DEFAULT_ACTION.formatted(MoveCommand.USAGE, ANY_YOU_WANT);
	}
	
	public GameFlowVO(PlayerManager manager, GameVO game, List<String> history) {
		this.order = manager.getPlayerOrder();
		this.nowTurn = manager.nowTurn().getAsMention();
		
		this.action = openHistory(history);
		
		Visitor<String> visitor = game.getVisitor();
		
		this.action += game.isFinished()
				? Visitor.ENDING_REPORT
				: DEFAULT_ACTION.formatted(MoveCommand.USAGE,
				visitor.visitCondition());
		
		this.position = visitor.calcNowPosition();
	}

	private String openHistory(List<String> history) {
		String result = history.remove(0);
		
		if (history.size() > 1) {
			result += history.remove(0);
		}
		
		if (history.size() > 1) {
			result += ContentsParser.OMIT;
		}
		
		if (history.size() > 0) {
			result += history.get(history.size() - 1);
		}

		return result;
	}

}
