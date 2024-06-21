package www.disbot.dfsGames.bot.exception;

public class OutOfStringLimitArgException extends Exception {
	private static final long serialVersionUID = 1L;

	public OutOfStringLimitArgException(int limit, String tooLong) {
		super("Too long arg found: "
				+ "expected at most %d but found %d letters\n"
						.formatted(limit, tooLong.length())
				+ "이전 사항에 맞추어 다시 작성해주세요.");
	}
}
