package www.disbot.dfsGames.bot.exception;

public class TooManyPeopleRequiredException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TooManyPeopleRequiredException(int criterion, int maxNum) {
		super(("Can't find %d people: "
				+ "you can gather %d people at most.")
					.formatted(criterion, maxNum)
				+ "\nPlease consider moving channel or gathering less people.");
	}
}
