package www.disbot.dfsGames.bot.command.impl;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import www.disbot.dfsGames.bot.command.Command;
import www.disbot.dfsGames.bot.command.impl.attend.UserNumberChecker;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.controller.args.AttendManager;
import www.disbot.dfsGames.bot.exception.AlreadyOpenChannelException;
import www.disbot.dfsGames.bot.exception.ArgsNumberDismatchException;
import www.disbot.dfsGames.bot.exception.NoGameFoundException;
import www.disbot.dfsGames.bot.parser.DiscordContents;
import www.disbot.dfsGames.bot.parser.impl.LaunchParser;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.CommandResultView;
import www.disbot.dfsGames.game.model.GameVO;
import www.disbot.dfsGames.game.model.LaunchVO;
import www.disbot.dfsGames.game.model.MetaInfoVO;
import www.disbot.dfsGames.game.search.GameFileManager;

public class LaunchCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "launch";
	public static final String EXPLAIN = "DFS 게임을 시작할게요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{"게임명", "(멤버 수)"};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	@Override
	public String[] getArgsNameArray() {
		return ARGS_NAME_ARRAY.clone();
	}
	
	@Override
	public View command(User user, GuildMessageChannel channel,
			Map<String, String> argsMap) throws Exception {
		if (argsMap.size() != ARGS_NAME_ARRAY.length) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		String gameName = argsMap.get(ARGS_NAME_ARRAY[0]);		
		GameVO game = gameSetup(gameName);
		
		String numString = argsMap.get(ARGS_NAME_ARRAY[1]);
		
		int playerNum = Integer.valueOf(numString);
		
		UserNumberChecker checker = new UserNumberChecker(channel);
		checker.isPlayable(game, playerNum);
		
		if (AttendManager.isOpen(channel)) {
			throw new AlreadyOpenChannelException(channel);
		}
		
		AttendManager.openTo(channel, playerNum, user);
		
		AttendManager.join(channel, user);
		
		LaunchVO result = new LaunchVO(game, AttendManager.calcStatus(channel));
		
		DiscordContents contents = new DiscordContents(new LaunchParser(result));
	   	
		contents.parse();
		
		return CommandResultView.builder()
	   			.title(USAGE)
	   			.contents(contents)
	   			.build();
	}

	private GameVO gameSetup(String gameName) throws Exception, NoGameFoundException {
		MetaInfoVO info = GameFileManager.getInstance()
				.extractMetaInfo(gameName);
		
		GameVO game;
		
		try {
			game = info.launchGame();
		}
		catch (Exception e) {
			throw new NoGameFoundException(gameName);
		}
		return game;
	}
}
