package www.disbot.dfsGames.bot.view.impl;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.view.DiscordView;

@Getter
@RequiredArgsConstructor
public class UnderPreparingView extends DiscordView {	
	public static final Color PREPAIR_COLOR = new Color(33, 100, 196);
	
	public static final String PREPAIR_TITLE_SUFFIX = " 준비중......";
	
	public static final String MAKER_MSG_TYPENAME = "msg";
	
	public static final String PREPAIR_FIELD_TYPENAME = "chaseup";
	
	public static final String PREPAIR_ALARM = "일해라 제작자: ";
	
	public static final String DEFAULT_MESSAGE = "해당 기능은 아직 구현중이에요.";
	
	@NonNull
	private String msg;
	
	@Setter
	private String makerUsername;
	
	@Override
	public <T> void init(Class<T> cls) throws Exception {
		String usage = extractUsage(cls);
		
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(usage)
				.setDescription(usage + PREPAIR_TITLE_SUFFIX)
				.setColor(PREPAIR_COLOR));
	}

	@Override
	public Pair<List<MessageEmbed>, File> close() {		
		List<MessageEmbed> embedList = new ArrayList<>();
		
		embedList.add(this.getEmbedBuilder()
				.addField(MAKER_MSG_TYPENAME,
						msg,
					false)
				.addField(PREPAIR_FIELD_TYPENAME,
						PREPAIR_ALARM + MENTION_FORMAT.formatted(makerUsername),
					false)
				.build());
		
		return new Pair<>(embedList, null);
	}
}
