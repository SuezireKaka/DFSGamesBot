package www.disbot.dfsGames.bot.command;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.bot.view.View;

public interface Command {
	public static final String PREFIX = "$";
	public static final String OR = "|";
	
	public static final String REGEX_ESCAPE = "\\";
	
	public static final int DISCORD_MAX_LENGTH = 2000;
	
	public String[] getArgsNameArray();
	
	public View command(User user, MessageChannel channel,
			Map<String, String> argsMap) throws Exception;
}
