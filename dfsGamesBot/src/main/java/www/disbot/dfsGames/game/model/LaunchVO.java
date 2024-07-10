package www.disbot.dfsGames.game.model;

import lombok.Getter;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;
import www.disbot.dfsGames.game.player.PlayerManager;

@Getter
public class LaunchVO extends CurrentUserStatusVO {
	public static final String LAUNCH_TITLE = "launched";
	public static final String LAUNCH_MESSAGE = "게임 \"%s\"이(가) 정상적으로 실행되었습니다.";
	
	public static final String GAME_START_MESSAGE = "유저 수가 충족되어 게임을 시작합니다.";
	
	private GameVO game;
	private PlayerManager manager;
	
	public LaunchVO(String message, int nowNumber, int requiredNumber,
			GameVO game, PlayerManager manager) {
		super(message, nowNumber, requiredNumber);
		this.game = game;
		this.manager = manager;
	}
}
