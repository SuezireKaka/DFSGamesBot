package www.disbot.dfsGames.game.model;

import lombok.Getter;
import lombok.Setter;
import www.disbot.dfsGames.game.player.PlayerManager;

@Getter
public class GameFlowVO {
	public static final String REPORT_TITLE = "report";
	
	public static final String PLAYER_ORDER_TITLE = "order";
	
	public static final String TURN_TITLE = "now turn";
	public static final String TURN_FORMAT = "%s님의 차례입니다.";
	
	@Setter
	private String report;
	
	private String order;
	private String nowTurn;

	public GameFlowVO(LaunchVO launch) {
		PlayerManager manager = launch.getManager();
		
		this.order = manager.getPlayerOrder();
		this.nowTurn = manager.getPlayerList().get(manager.getPlayerIndex())
				.getAsMention();
	}

}
