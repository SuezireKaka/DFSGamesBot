package www.disbot.dfsGames.bot.view.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.dfsGames.bot.model.data.GreetingVO;
import www.disbot.dfsGames.bot.view.DiscordView;

@Getter
@AllArgsConstructor
public class GreetingView extends DiscordView {
	public static final String GREETINNG_TITLE = "%s%s 등장!";
	public static final String GREETINNG_DESCRIPTION = "새로운 %s을 환영합니다~!";
	
	public static final String NEW_MEMBER = "새로 들어온 %s";
	
	public static final String PERSON_SUFFIX = "님";
	public static final String PERSON_TERM_OF_ADDRESS = "유저분";
	
	public static final String BOT_SUFFIX = "";
	public static final String BOT_TERM_OF_ADDRESS = "봇";
	
	private GreetingVO rawVO;

	@Override
	public <T> void init(Class<T> cls) {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(GREETINNG_TITLE.formatted(
						rawVO.getEffectiveName(),
						rawVO.isBot()
							? BOT_SUFFIX
							: PERSON_SUFFIX))
				.setDescription(GREETINNG_DESCRIPTION.formatted(
						rawVO.isBot()
							? BOT_TERM_OF_ADDRESS
							: PERSON_TERM_OF_ADDRESS))
				.setColor(SUCCESS_COLOR));
		
	}

	@Override
	public List<MessageEmbed> close() throws Exception {
		String url = rawVO.getAvatarUrl();
		String nick = rawVO.getEffectiveName();
		
		List<MessageEmbed> result = new ArrayList<>();
		
		result.add(this.getEmbedBuilder()
				.setImage(url)
				.addField(NEW_MEMBER
						.formatted(rawVO.isBot()
								? BOT_TERM_OF_ADDRESS
								: PERSON_TERM_OF_ADDRESS)
						, nick, false)
				.build());
		
		return result;
	}

}
