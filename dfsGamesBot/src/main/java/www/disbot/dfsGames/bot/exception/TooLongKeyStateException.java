package www.disbot.dfsGames.bot.exception;

import www.disbot.dfsGames.bot.parser.ContentsParser;

public class TooLongKeyStateException extends Exception {
	private static final long serialVersionUID = 1L;

	public TooLongKeyStateException(int length) {
		super("The length of a Key is too long: "
				+ "expected at most %d but found %d letters"
					.formatted(ContentsParser.MAX_ONE_VALUE_LENGTH, length));
	}
}
