package www.disbot.dfsGames.bot.exception;

import www.disbot.dfsGames.bot.controller.args.ArgsPacker;

public class NoCommandFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoCommandFoundException(String key, String[] inputArgs) {
		super("No command has been found that matches \"%s\""
					.formatted(ArgsPacker.usagePack(key, inputArgs)));
	}
}
