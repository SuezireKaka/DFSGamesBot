package www.disbot.dfsGames.bot.exception;

public class ArgsNumberDismatchException extends Exception {
	private static final long serialVersionUID = 1L;

	public ArgsNumberDismatchException(String[] inputArgs, String[] requiredArgs) {
		super("Input args can't match required args: "
				+ "expected %d args but found %d"
					.formatted(inputArgs.length, requiredArgs.length));
	}
}
