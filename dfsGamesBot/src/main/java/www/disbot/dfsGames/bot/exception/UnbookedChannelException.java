package www.disbot.dfsGames.bot.exception;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class UnbookedChannelException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnbookedChannelException(MessageChannel channel) {
		super("Channel \"%s\" is not open for this attendance"
					.formatted(channel.getAsMention()));
	}
}
