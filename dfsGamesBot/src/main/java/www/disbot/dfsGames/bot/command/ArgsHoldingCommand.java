package www.disbot.dfsGames.bot.command;

import java.util.Map;

public interface ArgsHoldingCommand extends Command {
	public String[] getAsyncArgsInfoArray();
	
	public int[] getAsyncArgsLimitArray();
	
	public int calcTotalArgsNumber();
	
	public boolean isArgsComplete(Map<String, String> argsMap, String[] asyncArgsArray) throws Exception;
}
