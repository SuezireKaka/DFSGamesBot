package www.disbot.dfsGames.bot.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class CurrentUserStatusVO {
	public static final String MESSAGE_TITLE = "message";
	public static final String REQUIRE_MESSAGE = "유저를 더 모집합니다.";
	public static final String FULL_MESSAGE = "유저 모집이 완료되었습니다.";
	
	public static final String STATUS_TITLE = "status";
	public static final String STATUS_FORMAT = "%d / %d";
	
	private String message;
	
	private int nowNumber;
	private int requiredNumber;
}
