package www.disbot.dfsGames.bot.command.impl;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import www.disbot.dfsGames.bot.command.Command;
import www.disbot.dfsGames.bot.command.impl.attend.UserNumberChecker;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.controller.args.AttendManager;
import www.disbot.dfsGames.bot.exception.ArgsNumberDismatchException;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;
import www.disbot.dfsGames.bot.parser.DiscordContents;
import www.disbot.dfsGames.bot.parser.impl.CurrentUserStatusParser;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.CommandResultView;

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
	public View command(User user, GuildMessageChannel channel,
			Map<String, String> argsMap) throws Exception {
		if (argsMap.size() != ARGS_NAME_ARRAY.length) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		String numString = argsMap.get(ARGS_NAME_ARRAY[0]);
		
		int userNum = Integer.valueOf(numString);
		
		UserNumberChecker checker = new UserNumberChecker(channel);
		checker.isPlayable(userNum);
		
		if (! AttendManager.isOpen(channel)) {
			AttendManager.openTo(channel, userNum, user);
		}
		
		AttendManager.join(channel, user);
		
		CurrentUserStatusVO result = AttendManager.calcStatus(channel);
		
		if (AttendManager.isFull(channel)) {
			AttendManager.forceClose(channel);
		}
		
		DiscordContents contents = new DiscordContents(new CurrentUserStatusParser(result));
	   	
		contents.parse();
		
		return CommandResultView.builder()
	   			.title(USAGE)
	   			.contents(contents)
	   			.build();
	}
}
