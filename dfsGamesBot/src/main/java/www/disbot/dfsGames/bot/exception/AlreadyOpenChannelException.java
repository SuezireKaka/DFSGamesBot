package www.disbot.dfsGames.bot.exception;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class AlreadyOpenChannelException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyOpenChannelException(MessageChannel channel) {
		super("Channel \"%s\" is already open."
					.formatted(channel.getAsMention()));
	}
}
