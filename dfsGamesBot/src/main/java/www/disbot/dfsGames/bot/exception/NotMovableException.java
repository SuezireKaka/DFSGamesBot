package www.disbot.dfsGames.bot.exception;

public class NotMovableException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotMovableException(String start, String end) {
		super("Can't move from %s to %s.".formatted(start, end)
				+ "\nPlease move to connected vertex unless someone completed a cluster.");
	}
}
