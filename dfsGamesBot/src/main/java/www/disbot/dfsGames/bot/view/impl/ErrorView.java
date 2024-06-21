package www.disbot.dfsGames.bot.view.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.dfsGames.bot.view.DiscordView;

@Getter
@AllArgsConstructor
public class ErrorView extends DiscordView {
	public static final String ERROR_TITLE = "에러 발생";
	
	public static final Color ERROR_COLOR = new Color(200, 31, 52);
	
	public static final String ERROR_FIELD_TYPENAME = "mention";
	
	public static final String ERROR_ALARM = "일단 모르겠고 제작자를 호출합니다: ";
	
	private Exception e;
	
	private String makerUsername;
	
	@Override
	public <T> void init(Class<T> cls) {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(ERROR_TITLE)
				.setDescription(e.getMessage())
				.setColor(ERROR_COLOR));
	}

	@Override
	public List<MessageEmbed> close() {		
		List<MessageEmbed> result = new ArrayList<>();
		
		result.add(this.getEmbedBuilder()
				.addField(ERROR_FIELD_TYPENAME,
					ERROR_ALARM + MENTION_FORMAT.formatted(makerUsername),
					false)
				.build());
		
		return result;
	}
}
