package www.disbot.dfsGames.bot.command.impl;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import www.disbot.dfsGames.bot.command.Command;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.exception.ArgsNumberDismatchException;
import www.disbot.dfsGames.bot.exception.NoVertexFoundException;
import www.disbot.dfsGames.bot.exception.NotPlayerException;
import www.disbot.dfsGames.bot.exception.NotStartedException;
import www.disbot.dfsGames.bot.exception.NotYourTurnException;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.UnderPreparingView;
import www.disbot.dfsGames.game.promise.PromiseManager;

public class MoveCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "move";
	public static final String EXPLAIN = "자신의 차례일 때 특정 위치로 이동해요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{"(이동할 위치)"};
	
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
		
		if (! PromiseManager.isStarted(channel)) {
			throw new NotStartedException();
		}
		
		if (! PromiseManager.isPlayer(channel, user)) {
			throw new NotPlayerException(channel);
		}
		
		User nowTurn = PromiseManager.nowTurn(channel);
		
		if (! user.equals(nowTurn)) {
			throw new NotYourTurnException(nowTurn);
		}
		
		String vertex = argsMap.get(ARGS_NAME_ARRAY[0]);
		
		if (! PromiseManager.hasVertex(channel, vertex)) {
			throw new NoVertexFoundException(
					PromiseManager.getGameName(channel), vertex);
		}
		
		return new UnderPreparingView(UnderPreparingView.DEFAULT_MESSAGE);
	}
}
