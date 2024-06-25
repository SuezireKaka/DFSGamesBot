package www.disbot.dfsGames.bot.exception;

public class NoGameFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoGameFoundException(String name) {
		super("No game has been found that has name \"%s\""
					.formatted(name));
	}
}
