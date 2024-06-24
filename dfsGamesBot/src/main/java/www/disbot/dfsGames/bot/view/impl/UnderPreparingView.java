package www.disbot.dfsGames.bot.view.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.dfsGames.bot.view.DiscordView;

@Getter
public class UnderPreparingView extends DiscordView {	
	public static final Color PREPAIR_COLOR = new Color(33, 100, 196);
	
	public static final String PREPAIR_FIELD_TYPENAME = "chaseup";
	
	public static final String PREPAIR_ALARM = "일해라 제작자: ";
	
	@Setter
	private String makerUsername;
	
	@Override
	public <T> void init(Class<T> cls) throws Exception {
		String usage = extractUsage(cls);
		
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(usage)
				.setDescription(usage + RESULT_TITLE_SUFFIX)
				.setColor(PREPAIR_COLOR));
	}

	@Override
	public List<MessageEmbed> close() {		
		List<MessageEmbed> result = new ArrayList<>();
		
		result.add(this.getEmbedBuilder()
				.addField(PREPAIR_FIELD_TYPENAME,
						PREPAIR_ALARM + MENTION_FORMAT.formatted(makerUsername),
					false)
				.build());
		
		return result;
	}
}
