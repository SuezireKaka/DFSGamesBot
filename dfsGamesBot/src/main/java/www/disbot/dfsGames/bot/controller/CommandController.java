package www.disbot.dfsGames.bot.controller;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.bot.command.Command;
import www.disbot.dfsGames.bot.command.api.WebClientRequestStrategy;
import www.disbot.dfsGames.bot.command.impl.AllGamesCommand;
import www.disbot.dfsGames.bot.command.impl.GameInfoCommand;
import www.disbot.dfsGames.bot.command.impl.HelloWorldCommand;
import www.disbot.dfsGames.bot.command.impl.LaunchCommand;
import www.disbot.dfsGames.bot.command.impl.ListAllCommand;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.exception.NoCommandFoundException;
import www.disbot.dfsGames.bot.view.View;

public class CommandController {
	
	public View execute(User user, MessageChannel channel,
			String key, String[] args, String asyncMessage,
			WebClientRequestStrategy requester) throws Exception {
		Map<String, String> packedArgs;
		
		View result = null;
		
		if (key.equalsIgnoreCase(HelloWorldCommand.COMMAND)
				&& args.length == new HelloWorldCommand().getArgsNameArray().length) {
			
            packedArgs = new ArgsPacker<HelloWorldCommand>()
            		.mapPack(new HelloWorldCommand(), args);
            
            result = new HelloWorldCommand().command(user, channel, packedArgs);
            result.init(HelloWorldCommand.class);
        }
		
		else if (key.equalsIgnoreCase(ListAllCommand.COMMAND)
        		&& args.length == new ListAllCommand().getArgsNameArray().length) {
			
        	packedArgs = new ArgsPacker<ListAllCommand>()
                	.mapPack(new ListAllCommand(), args);

        	result = new ListAllCommand().command(user, channel, packedArgs);
        	result.init(ListAllCommand.class);
        }
		
		else if (key.equalsIgnoreCase(AllGamesCommand.COMMAND)
        		&& args.length == new AllGamesCommand().getArgsNameArray().length) {
			
        	packedArgs = new ArgsPacker<AllGamesCommand>()
                	.mapPack(new AllGamesCommand(), args);

        	result = new AllGamesCommand().command(user, channel, packedArgs);
        	result.init(AllGamesCommand.class);
        }
		
		else if (key.equalsIgnoreCase(GameInfoCommand.COMMAND)
        		&& args.length == new LaunchCommand().getArgsNameArray().length) {
			
        	packedArgs = new ArgsPacker<GameInfoCommand>()
                	.mapPack(new GameInfoCommand(), args);

        	result = new GameInfoCommand().command(user, channel, packedArgs);
        	result.init(GameInfoCommand.class);
        }
		
		else if (key.equalsIgnoreCase(LaunchCommand.COMMAND)
        		&& args.length == new LaunchCommand().getArgsNameArray().length) {
			
        	packedArgs = new ArgsPacker<LaunchCommand>()
                	.mapPack(new LaunchCommand(), args);

        	result = new LaunchCommand().command(user, channel, packedArgs);
        	result.init(LaunchCommand.class);
        }
		
		else if (key.startsWith(Command.PREFIX) && ! key.equals(Command.PREFIX)) {
			throw new NoCommandFoundException(key, args);
		}
		
		return result;
	}
}
