package www.disbot.dfsGames.bot.exception;

public class NoVertexFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoVertexFoundException(String game, String vertex) {
		super("The game \"%s\" does not have vertex named \"%s\""
					.formatted(game, vertex));
	}
}
