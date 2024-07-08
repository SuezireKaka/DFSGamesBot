package www.disbot.dfsGames.bot.controller.args;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.DFSGamesBotApplication;
import www.disbot.dfsGames.bot.exception.AlreadyOpenChannelException;
import www.disbot.dfsGames.bot.exception.NotMakerException;
import www.disbot.dfsGames.bot.exception.UnbookedChannelException;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;
import www.disbot.dfsGames.game.player.PlayerManager;

public abstract class AttendManager {
	private static final Map<MessageChannel, PlayerManager> attendChannelState =
			new HashMap<>();
	
	public static boolean isOpen(MessageChannel channel) {
		return attendChannelState.containsKey(channel);
	}
	
	public static boolean isFull(MessageChannel channel) {
		return attendChannelState.get(channel)
				.isFull();
	}
	
	public static void openTo(MessageChannel channel, int userNum, User user) throws Exception {
		if (attendChannelState.containsKey(channel)) {
			throw new AlreadyOpenChannelException(channel);
		}
		
		attendChannelState.put(channel,
				new PlayerManager(userNum, channel, user));
	}
	
	public static String close(MessageChannel channel, User user) throws Exception {
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		User maker = attendChannelState.get(channel).getMaker();
		
		if (user != maker && ! user.getId().equals(
				DFSGamesBotApplication.callMaker().getId())) {
			throw new NotMakerException(channel);
		}
		
		attendChannelState.remove(channel);
		
		return user.equals(maker)
				? "성공적으로"
				: "봇 제작자에 의해";
	}
	
	public static void forceClose(MessageChannel channel) throws Exception {
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

	public static CurrentUserStatusVO calcStatus(GuildMessageChannel channel) {
		PlayerManager manager = attendChannelState.get(channel);
		
		CurrentUserStatusVO result = new CurrentUserStatusVO(
				manager.getPlayerList().size(), manager.getMaxNum());
		
		result.setMessage(isFull(channel)
				? CurrentUserStatusVO.FULL_MESSAGE
				: CurrentUserStatusVO.REQUIRE_MESSAGE);
		
		return result;

	}
}
