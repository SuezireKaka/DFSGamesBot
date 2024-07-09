package www.disbot.dfsGames.bot.exception;

public class TooLessPeopleRequiredException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TooLessPeopleRequiredException() {
		super("You can gather at least one person including you.");
	}
}
