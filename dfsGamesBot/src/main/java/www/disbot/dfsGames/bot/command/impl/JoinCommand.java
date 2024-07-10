package www.disbot.dfsGames.bot.command.impl;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import www.disbot.dfsGames.bot.command.Command;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.exception.ArgsNumberDismatchException;
import www.disbot.dfsGames.bot.exception.UnbookedChannelException;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;
import www.disbot.dfsGames.bot.parser.DiscordContents;
import www.disbot.dfsGames.bot.parser.impl.CurrentUserStatusParser;
import www.disbot.dfsGames.bot.parser.impl.LaunchParser;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.CommandResultView;
import www.disbot.dfsGames.game.model.LaunchVO;
import www.disbot.dfsGames.game.promise.PromiseManager;

public class JoinCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "join";
	public static final String EXPLAIN = "이 체널에 열린 약속 포인트에 참가해요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	public static final String EXTENSION_POINT = ".";
	
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
		
		if (! PromiseManager.isOpen(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		PromiseManager.join(channel, user);
		
		CurrentUserStatusVO result = PromiseManager.calcStatus(channel);
		
		if (PromiseManager.isFull(channel)) {
			if (PromiseManager.isAttendType(channel)) {
				
				PromiseManager.forceClose(channel);
			}
			else {
				PromiseManager.startGame(channel);
				result = PromiseManager.calcStatus(channel);
			}
		}
		
		DiscordContents contents = PromiseManager.isAttendType(channel)
				? new DiscordContents(new CurrentUserStatusParser(result))
				: new DiscordContents(new LaunchParser((LaunchVO) result));
	   	
		contents.parse();
		
		return CommandResultView.builder()
	   			.title(USAGE)
	   			.contents(contents)
	   			.build();
	}
}
