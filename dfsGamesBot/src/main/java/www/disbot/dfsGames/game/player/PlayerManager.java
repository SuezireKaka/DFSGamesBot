package www.disbot.dfsGames.game.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import net.dv8tion.jda.api.entities.User;

@Getter
public class PlayerManager {
	private List<User> playerList = new ArrayList<>();
	private int playerIndex;
	
	public void join(User user) {
		playerList.add(user);
	}
	
	public void shuffle() {
		Collections.shuffle(playerList);
	}
	
	public User nextTurn() {
		playerIndex = (playerIndex + 1) % playerList.size();
		
		return playerList.get(playerIndex);
	}
}
