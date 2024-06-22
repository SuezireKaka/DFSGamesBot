package www.disbot.dfsGames.bot.controller;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.dfsGames.bot.command.api.WebClientRequestStrategy;
import www.disbot.dfsGames.bot.command.impl.AllGamesCommand;
import www.disbot.dfsGames.bot.command.impl.HelloWorldCommand;
import www.disbot.dfsGames.bot.command.impl.ListAllCommand;
import www.disbot.dfsGames.bot.controller.args.ArgsPacker;
import www.disbot.dfsGames.bot.view.View;

public class CommandController {
	
	public View execute(User user, String key, String[] args, String asyncMessage,
			WebClientRequestStrategy requester) throws Exception {
		Map<String, String> packedArgs;
		
		View result = null;
		
		if (key.equalsIgnoreCase(HelloWorldCommand.COMMAND)
				&& args.length == new HelloWorldCommand().getArgsNameArray().length) {
			
            packedArgs = new ArgsPacker<HelloWorldCommand>()
            		.mapPack(new HelloWorldCommand(), args);
            
            result = new HelloWorldCommand().command(user, packedArgs);
            result.init(HelloWorldCommand.class);
        }
		
		else if (key.equalsIgnoreCase(ListAllCommand.COMMAND)
        		&& args.length == new ListAllCommand().getArgsNameArray().length) {
			
        	packedArgs = new ArgsPacker<ListAllCommand>()
                	.mapPack(new ListAllCommand(), args);

        	result = new ListAllCommand().command(user, packedArgs);
        	result.init(ListAllCommand.class);
        }
		
		else if (key.equalsIgnoreCase(AllGamesCommand.COMMAND)
        		&& args.length == new AllGamesCommand().getArgsNameArray().length) {
			
        	packedArgs = new ArgsPacker<AllGamesCommand>()
                	.mapPack(new AllGamesCommand(), args);

        	result = new AllGamesCommand().command(user, packedArgs);
        	result.init(AllGamesCommand.class);
        }
		
		return result;
	}
}
