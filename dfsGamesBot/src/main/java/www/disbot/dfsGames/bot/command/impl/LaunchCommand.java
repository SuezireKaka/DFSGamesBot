package www.disbot.dfsGames.bot.command.impl;

import java.io.File;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.dfsGames.bot.command.Command;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.exception.ArgsNumberDismatchException;
import www.disbot.dfsGames.bot.exception.NoGameFoundException;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.UnderPreparingView;
import www.disbot.dfsGames.game.search.GameFileManager;

public class LaunchCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "launch";
	public static final String EXPLAIN = "DFS 게임을 시작할게요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{"게임명"};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	@Override
	public String[] getArgsNameArray() {
		return ARGS_NAME_ARRAY.clone();
	}
	
	@Override
	public View command(User user, Map<String, String> argsMap) throws Exception {
		if (argsMap.size() != ARGS_NAME_ARRAY.length) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		String gameName = argsMap.get(ARGS_NAME_ARRAY[0]);
		
		File gameFile = GameFileManager.getInstance().findGameFileWithName(gameName);
		
		if (gameFile == null) {
			throw new NoGameFoundException(gameName);
		}
		
		//DiscordContents contents = new DiscordContents(new HelloWorldParser(result));
	   	
		//contents.parse();
		
	   	return new UnderPreparingView("금방 만들겠습니다 제성합니다");
	}
}
