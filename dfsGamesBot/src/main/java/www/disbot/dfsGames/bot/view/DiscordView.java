package www.disbot.dfsGames.bot.view;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.EmbedBuilder;

@Getter
@Setter
public abstract class DiscordView implements View {
	private EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor("DFS Games");
}
