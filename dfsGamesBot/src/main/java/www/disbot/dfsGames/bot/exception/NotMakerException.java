package www.disbot.dfsGames.bot.exception;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class NotMakerException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotMakerException(MessageChannel channel) {
		super("You haven't made this attandance on \"%s\"."
					.formatted(channel.getAsMention()));
	}
}
