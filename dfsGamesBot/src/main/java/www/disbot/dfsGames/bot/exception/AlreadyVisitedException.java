package www.disbot.dfsGames.bot.exception;

public class AlreadyVisitedException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyVisitedException(String vertex) {
		super("You have visited \"%s\" in this game."
					.formatted(vertex));
	}
}
