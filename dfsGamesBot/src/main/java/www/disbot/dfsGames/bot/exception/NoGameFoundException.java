package www.disbot.dfsGames.bot.exception;

import www.disbot.dfsGames.bot.controller.args.ArgsPacker;

public class NoGameFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoGameFoundException(String key, String[] inputArgs) {
		super("No game has been found that has name \"%s\""
					.formatted(ArgsPacker.usagePack(key, inputArgs)));
	}
}
