package www.disbot.dfsGames.bot.view.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.DiscordContents;
import www.disbot.dfsGames.bot.parser.ContentsParser.ParseType;
import www.disbot.dfsGames.bot.view.DiscordView;

@Getter
@Builder
public class CommandResultView extends DiscordView {
	private String title;
	private DiscordContents contents;
	
	@Override
	public <T> void init(Class<T> cls) throws Exception {
		String usage = (String) cls.getField("USAGE").get("");
		
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(usage)
				.setDescription(usage + RESULT_TITLE_SUFFIX)
				.setColor(SUCCESS_COLOR));
	}

	@Override
	public List<MessageEmbed> close() throws Exception {
		List<List<Pair<ParseType, String>>> parsedContents = contents.getContents();
		
		List<MessageEmbed> result = new ArrayList<>();
		
		for (List<Pair<ParseType, String>> lemma : parsedContents) {
			EmbedBuilder safeBuilder = this.getEmbedBuilder();
			
			for (int i = 0; i < lemma.size(); i++) {
				String key = lemma.size() % 2 == i + 1
						? ""
						: lemma.get(i).getSecond();
				
				i++;
				
				String val = lemma.get(i).getSecond();
				
				safeBuilder.addField(key, val, false);
			}
			
			result.add(safeBuilder.build());
		}
		
		return result;
	}

}
