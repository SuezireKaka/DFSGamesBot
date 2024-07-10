package www.disbot.dfsGames.game.model;

import lombok.Getter;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;

@Getter
public class LaunchVO extends CurrentUserStatusVO {
	public static final String MESSAGE_TITLE = "launched";
	public static final String LAUNCH_MESSAGE = "게임 \"%s\"이(가) 정상적으로 실행되었습니다.";
	
	private GameVO game;
	
	public LaunchVO(int nowNumber, int requiredNumber, GameVO game) {
		super(nowNumber, requiredNumber);
		this.game = game;
	}
}
