package www.disbot.dfsGames.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import www.disbot.dfsGames.bot.command.impl.MoveCommand;
import www.disbot.dfsGames.game.player.PlayerManager;

@Getter
@AllArgsConstructor
public class GameFlowVO {
	public static final String ACTION_TITLE = "action";
	
	public static final String PLAYER_ORDER_TITLE = "players";
	
	public static final String POSITION_TITLE = "position";
	
	public static final String TURN_TITLE = "now turn";
	public static final String TURN_FORMAT = "%s님의 차례입니다.";
	
	public static final String FIRST_TURN_ACTION = "\"%s\" 명령어로 %s 위치로 이동해주세요.";
	
	public static final String ANY_YOU_WANT = "원하시는";
	
	public static final String NEIBHOR_OF = "{%s}에 인접한";
	public static final String NOT_VISITED = "이전에 방문한 적 없는";
	
	private String order;
	private String nowTurn;
	
	private String position = null;
	
	private String action;

	public GameFlowVO(LaunchVO launch) {
		PlayerManager manager = launch.getManager();
		
		this.order = manager.getPlayerOrder();
		this.nowTurn = manager.getPlayerList().get(manager.getPlayerIndex())
				.getAsMention();
		
		this.action = FIRST_TURN_ACTION.formatted(MoveCommand.USAGE, ANY_YOU_WANT);
	}

}
