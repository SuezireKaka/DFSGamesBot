package www.disbot.dfsGames.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;

@Getter
@AllArgsConstructor
public class LaunchVO {
	public static final String MESSAGE_TITLE = "launched";
	public static final String LAUNCH_MESSAGE = "게임 \"%s\"이(가) 정상적으로 실행되었습니다.";
	
	private GameVO game;
	private CurrentUserStatusVO status;
}
