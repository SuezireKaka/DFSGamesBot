package www.disbot.dfsGames.bot.exception;

public class AlreadyStartedGameException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyStartedGameException() {
		super("The game you want to join has already started.");
	}
}
