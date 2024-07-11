package www.disbot.dfsGames.game.promise;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.DFSGamesBotApplication;
import www.disbot.dfsGames.bot.exception.AlreadyStartedGameException;
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
	
	public static boolean isStarted(MessageChannel channel) {
		if (isOpen(channel)) {
			GameVO game = attendChannelState.get(channel).getGame();
			return game != null && game.isStarted();
		}
		
		return false;
	}
	
	public static boolean isAttendType(GuildMessageChannel channel) {
		if (! isOpen(channel)) {
			return true;
		}
		
		PromiseType type = attendChannelState.get(channel).getType();
		return type == PromiseType.ATTEND;
	}
	
	public static boolean isPlayer(GuildMessageChannel channel, User user) {
		return attendChannelState.get(channel).getManager()
				.getPlayerList()
				.contains(user);
	}
	
	public static boolean hasVertex(GuildMessageChannel channel, String vertex) {
		GameVO game = attendChannelState.get(channel).getGame();
		return game != null && game.getBackground().hasVertex(vertex);
	}
	
	public static void openTo(MessageChannel channel,
			int userNum, User user, PromiseType type) {
		
		attendChannelState.put(channel, new PromisePoint(
				type, new PlayerManager(userNum, channel, user)));
	}
	
	public static void launchGameTo(MessageChannel channel, GameVO game) {
		attendChannelState.get(channel).setGame(game);
	}
	
	public static String getGameName(MessageChannel channel) {
		return attendChannelState.get(channel).getGame().getName();
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

	public static void join(MessageChannel channel, User user)
			throws Exception {
		
		if (! attendChannelState.containsKey(channel)) {
			throw new UnbookedChannelException(channel);
		}
		
		if (isStarted(channel)) {
			throw new AlreadyStartedGameException();
		}
		
		PromisePoint point = attendChannelState.get(channel);
		
		point.getManager().join(user);
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
		
		PlayerManager manager = promise.getManager();
		GameVO game = promise.getGame();
		
		int playerNum = manager.getPlayerList().size();
		int maxNum = manager.getMaxNum();
		
		String message = isFull(channel)
				? isAttendType(channel)
					? CurrentUserStatusVO.FULL_MESSAGE
					: LaunchVO.GAME_START_MESSAGE
				: CurrentUserStatusVO.REQUIRE_MESSAGE;
		
		CurrentUserStatusVO result = isAttendType(channel)
				? new CurrentUserStatusVO(message, playerNum, maxNum)
				: new LaunchVO(message, playerNum, maxNum, game, manager);
		
		return result;
	}

	public static void startGame(GuildMessageChannel channel) {
		PromisePoint promise = attendChannelState.get(channel);
		
		PlayerManager manager = promise.getManager();
		GameVO game = promise.getGame();
		
		manager.shuffle();
		game.start();
	}
	
	public static User nowTurn(GuildMessageChannel channel) {
		return attendChannelState.get(channel).getManager().nowTurn();
	}
}
