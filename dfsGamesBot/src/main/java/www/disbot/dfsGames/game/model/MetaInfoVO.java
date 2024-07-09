package www.disbot.dfsGames.game.model;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MetaInfoVO {
	public static final String DIRECTED = "D";
	
	private boolean isDirected;
	private String name;
	private String introduce;
	
	private File gameFile;
	
	public GameVO launchGame() throws Exception {
		return new GameVO(name, gameFile);
	}
}
