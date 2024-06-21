package www.disbot.dfsGames.bot.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class GreetingVO {
	private String effectiveName;
	private String avatarUrl;
	private boolean isBot;
}
