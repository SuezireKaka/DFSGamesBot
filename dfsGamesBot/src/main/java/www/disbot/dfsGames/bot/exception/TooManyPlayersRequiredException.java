package www.disbot.dfsGames.bot.exception;

import www.disbot.dfsGames.game.model.GameVO;

public class TooManyPlayersRequiredException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TooManyPlayersRequiredException(GameVO game, int maxNum, int criterion) {
		super(("Game \"%s\" requires %d people at most but found %d.")
					.formatted(game.getName(), maxNum, criterion)
				+ "\nPlease try to play with less people.");
	}
}
