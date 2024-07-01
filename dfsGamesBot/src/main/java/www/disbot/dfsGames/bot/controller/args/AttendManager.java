package www.disbot.dfsGames.bot.controller.args;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.bot.exception.UnbookedChannelException;
import www.disbot.dfsGames.game.player.PlayerManager;

public abstract class AttendManager {
	private static final Map<MessageChannel, PlayerManager> attendChannelState =
			new HashMap<>();
	
	public void openTo(MessageChannel channel) throws Exception {
		if (attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
	}
	
	public void close(MessageChannel channel) throws Exception {
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		attendChannelState.remove(channel);
	}

	public void join(MessageChannel channel, User user) throws Exception {
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		attendChannelState.get(channel).join(user);
	}
}
