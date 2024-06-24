package www.disbot.dfsGames.game.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import net.dv8tion.jda.api.entities.User;
import www.disbot.dfsGames.game.graph.AdjacencyMapGraph;
import www.disbot.dfsGames.game.graph.Graph;
import www.disbot.dfsGames.game.visitor.Visitor;

@Getter
public class GameVO {
	private String name;
	private Visitor visitor;
	private Graph<String> background = new AdjacencyMapGraph<>();
	private List<User> playerList = new ArrayList<>();
	
	public GameVO(File gameFile) {
		
	}
}
