package www.disbot.dfsGames.bot.command.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.entities.User;
import www.disbot.dfsGames.DFSGamesBotApplication;
import www.disbot.dfsGames.api.framework.model.structure.Page;
import www.disbot.dfsGames.bot.command.Command;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.exception.ArgsNumberDismatchException;
import www.disbot.dfsGames.bot.model.data.HelloWorldVO;
import www.disbot.dfsGames.bot.parser.DiscordContents;
import www.disbot.dfsGames.bot.parser.impl.HelloWorldParser;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.CommandResultView;

public class AllGamesCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "allGames";
	public static final String EXPLAIN = "모든 DFS 게임의 목록을 가져올게요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{"(페이지 수)"};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	public static final String EXTENSION_POINT = ".";
	
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
		
		String pageString = argsMap.get(ARGS_NAME_ARRAY[0]);
		
		int pageNum = Integer.valueOf(pageString);
		
		File directory = new File(DFSGamesBotApplication.callGameSetup().getGameAddress());
		
		List<File> gameFilesList = Arrays.stream(directory.listFiles())
				.collect(Collectors.toList());
		
		List<String> gameNamesOnPage = gameFilesList.subList(0, 1).stream()
				.map(file -> {
					String fullName = file.getName();
					return fullName.substring(0, fullName.lastIndexOf("."));
				})
				.collect(Collectors.toList());
		
		Page<String> page = new Page<>(pageNum, gameNamesOnPage);
		page.applyFoundNumber(gameFilesList.size());
		
		HelloWorldVO result = new HelloWorldVO("Hello, world!");
		
		DiscordContents contents = new DiscordContents(new HelloWorldParser(result));
	   	
		contents.parse();
		
	   	return CommandResultView.builder()
	   			.title(USAGE)
	   			.contents(contents)
	   			.build();
	}
}
