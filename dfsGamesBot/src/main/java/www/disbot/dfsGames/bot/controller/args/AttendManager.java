package www.disbot.dfsGames.bot.controller.args;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.bot.exception.AlreadyOpenChannelException;
import www.disbot.dfsGames.bot.exception.UnbookedChannelException;
import www.disbot.dfsGames.game.player.PlayerManager;

public abstract class AttendManager {
	private static final Map<MessageChannel, PlayerManager> attendChannelState =
			new HashMap<>();
	
	public static boolean isOpen(MessageChannel channel) {
		return attendChannelState.containsKey(channel);
	}
	
	public static void openTo(MessageChannel channel, int userNum) throws Exception {
		if (attendChannelState.containsKey(channel)) {
			throw new AlreadyOpenChannelException(channel);
		}
		
		attendChannelState.put(channel, new PlayerManager(userNum, channel));
	}
	
	public static void close(MessageChannel channel) throws Exception {
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		attendChannelState.remove(channel);
	}

	public static void join(MessageChannel channel, User user) throws Exception {
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		attendChannelState.get(channel).join(user);
	}
}
