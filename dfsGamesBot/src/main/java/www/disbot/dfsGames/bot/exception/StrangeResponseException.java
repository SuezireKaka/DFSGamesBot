package www.disbot.dfsGames.bot.exception;

public class StrangeResponseException extends Exception {
	private static final long serialVersionUID = 1L;

	public StrangeResponseException(String strangeResult) {
		super("Found such a strange response that cannot be handled properly:\n%s".formatted(strangeResult));
	}
}
