package www.disbot.dfsGames.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import www.disbot.dfsGames.game.player.PlayerManager;

@Getter
@AllArgsConstructor
public class GameFlowVO {
	public static final String ACTION_TITLE = "action";
	
	public static final String PLAYER_ORDER_TITLE = "players";
	
	public static final String TURN_TITLE = "now turn";
	public static final String TURN_FORMAT = "%s님의 차례입니다.";
	
	public static final String FIRST_TURN_ACTION = "$s 명령어로 원하는 위치로 이동해주세요.";
	
	private String order;
	private String nowTurn;
	
	private String action;

	public GameFlowVO(LaunchVO launch) {
		PlayerManager manager = launch.getManager();
		
		this.order = manager.getPlayerOrder();
		this.nowTurn = manager.getPlayerList().get(manager.getPlayerIndex())
				.getAsMention();
		
		this.action = FIRST_TURN_ACTION;
	}

}
