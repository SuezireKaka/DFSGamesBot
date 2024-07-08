package www.disbot.dfsGames.game.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.bot.exception.AlreadyJoinedUserException;

@Getter
public class PlayerManager {
	private int maxNum;
	private MessageChannel channel;
	
	public PlayerManager(int maxNum, MessageChannel channel) {
		this.maxNum = maxNum;
		this.channel = channel;
	}
	
	private List<User> playerList = new ArrayList<>();
	private int playerIndex = 0;
	
	public void join(User user) throws Exception {
		if (playerList.contains(user)) {
			throw new AlreadyJoinedUserException(channel);
		}
		
		playerList.add(user);
	}
	
	public boolean isFull() {
		return playerList.size() >= maxNum;
	}
	
	public void shuffle() {
		Collections.shuffle(playerList);
	}
	
	public User nextTurn() {
		playerIndex = (playerIndex + 1) % playerList.size();
		
		return playerList.get(playerIndex);
	}
}
