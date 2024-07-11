package www.disbot.dfsGames.bot.exception;

import net.dv8tion.jda.api.entities.User;

public class NotYourTurnException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotYourTurnException(User user) {
		super(("It's not your turn; it's %s's turn now."
				+ "\nIf the player has disappeared, please contact to game launcher or bot maker."
				).formatted(user.getAsMention()));
	}
}
