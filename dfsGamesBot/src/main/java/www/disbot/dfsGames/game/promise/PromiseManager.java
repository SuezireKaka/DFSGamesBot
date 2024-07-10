package www.disbot.dfsGames.game.promise;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.DFSGamesBotApplication;
import www.disbot.dfsGames.bot.exception.NotMakerException;
import www.disbot.dfsGames.bot.exception.UnbookedChannelException;
import www.disbot.dfsGames.bot.model.data.CurrentUserStatusVO;
import www.disbot.dfsGames.game.model.GameVO;
import www.disbot.dfsGames.game.model.LaunchVO;
import www.disbot.dfsGames.game.player.PlayerManager;

public abstract class PromiseManager {
	private static final Map<MessageChannel, PromisePoint> attendChannelState =
			new HashMap<>();
	
	public static boolean isOpen(MessageChannel channel) {
		return attendChannelState.containsKey(channel);
	}
	
	public static boolean isFull(MessageChannel channel) {
		return attendChannelState.get(channel).getManager()
				.isFull();
	}
	
	public static void openTo(MessageChannel channel,
			int userNum, User user, PromiseType type) {
		
		attendChannelState.put(channel, new PromisePoint(
				type, new PlayerManager(userNum, channel, user)));
	}
	
	public static void launchGameTo(MessageChannel channel, GameVO game) {
		attendChannelState.get(channel).setGame(game);
	}
	
	public static String close(MessageChannel channel, User user)
			throws Exception {
		
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		User maker = attendChannelState.get(channel).getManager().getMaker();
		
		if (user != maker && ! user.getId().equals(
				DFSGamesBotApplication.callMaker().getId())) {
			throw new NotMakerException(channel);
		}
		
		attendChannelState.remove(channel);
		
		return user.equals(maker)
				? "성공적으로"
				: "봇 제작자에 의해";
	}

	public static PromisePoint join(MessageChannel channel, User user)
			throws Exception {
		
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		PromisePoint point = attendChannelState.get(channel);
		
		point.getManager().join(user);
		
		return point;
	}
	
	public static void forceClose(MessageChannel channel)
			throws Exception {
		
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		attendChannelState.remove(channel);
	}

	public static CurrentUserStatusVO calcStatus(GuildMessageChannel channel) {
		PromisePoint promise = attendChannelState.get(channel);
		
		PromiseType type = promise.getType();
		
		PlayerManager manager = promise.getManager();
		GameVO game = promise.getGame();
		
		int playerNum = manager.getPlayerList().size();
		int maxNum = manager.getMaxNum();
		
		CurrentUserStatusVO result = type == PromiseType.ATTEND
				? new CurrentUserStatusVO(playerNum, maxNum)
				: new LaunchVO(playerNum, maxNum, game);
		
		result.setMessage(isFull(channel)
				? CurrentUserStatusVO.FULL_MESSAGE
				: CurrentUserStatusVO.REQUIRE_MESSAGE);
		
		return result;

	}
}
