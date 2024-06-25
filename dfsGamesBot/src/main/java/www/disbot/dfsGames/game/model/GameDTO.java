package www.disbot.dfsGames.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameDTO {
	public static final String DIRECTED = "D";
	
	private boolean directed;
	private String name;
	private String introduce;
}
