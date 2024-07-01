package www.disbot.dfsGames.bot.command.impl;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.bot.command.Command;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.exception.ArgsNumberDismatchException;
import www.disbot.dfsGames.bot.parser.DiscordContents;
import www.disbot.dfsGames.bot.parser.impl.GameInfoParser;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.CommandResultView;
import www.disbot.dfsGames.game.model.MetaInfoVO;
import www.disbot.dfsGames.game.search.GameFileManager;

public class AttendCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "attend";
	public static final String EXPLAIN = "특정 수의 멤버들의 출석을 확인해요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{"(멤버 수)"};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	@Override
	public String[] getArgsNameArray() {
		return ARGS_NAME_ARRAY.clone();
	}
	
	@Override
	public View command(User user, MessageChannel channel,
			Map<String, String> argsMap) throws Exception {
		if (argsMap.size() != ARGS_NAME_ARRAY.length) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		String numString = argsMap.get(ARGS_NAME_ARRAY[0]);
		
		int userNum = Integer.valueOf(numString);
		
		String gameName = argsMap.get(ARGS_NAME_ARRAY[0]);
		
		MetaInfoVO result = GameFileManager.getInstance()
				.extractMetaInfo(gameName);
		
		DiscordContents contents = new DiscordContents(new GameInfoParser(result));
	   	
		contents.parse();
		
		return CommandResultView.builder()
	   			.title(USAGE)
	   			.contents(contents)
	   			.build();
	}
}
