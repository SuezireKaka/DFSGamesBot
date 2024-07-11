package www.disbot.dfsGames.bot.exception;

public class NotStartedException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotStartedException() {
		super("The game on this channel has not been started.");
	}
}
