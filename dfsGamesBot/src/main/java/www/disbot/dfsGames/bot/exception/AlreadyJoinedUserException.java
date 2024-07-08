package www.disbot.dfsGames.bot.exception;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class AlreadyJoinedUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyJoinedUserException(MessageChannel channel) {
		super("You are already joined to attandance on \"%s\"."
					.formatted(channel.getAsMention()));
	}
}
