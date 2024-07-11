package www.disbot.dfsGames.bot.exception;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class NotPlayerException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotPlayerException(MessageChannel channel) {
		super(("Can't find you from players list of the game on channel \"%s\""
				).formatted(channel.getAsMention()));
	}
}
