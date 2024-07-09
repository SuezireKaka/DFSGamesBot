package www.disbot.dfsGames.game.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import lombok.Getter;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.game.graph.AdjacencyMapGraph;
import www.disbot.dfsGames.game.graph.Graph;
import www.disbot.dfsGames.game.visitor.Visitor;

@Getter
public class GameVO {
	public static final String DATA_SEPERATOR = "\r\n\r\n";
	public static final String EDGE_SEPERATOR = "\r\n";
	public static final String VERTEX_SEPERATOR = ",";
	
	private String name;
	private String description;
	private Visitor<String> visitor = new Visitor<>();
	private Graph<String> background = new AdjacencyMapGraph<>();
	
	public GameVO(String name, File gameFile) throws IOException {
		this.name = name;
		
		// 게임 csv 참조
		String[] gameData = Files.readString(gameFile.toPath())
				.split(DATA_SEPERATOR);
		
		boolean isDirected = gameData[0].equals(MetaInfoVO.DIRECTED);
		
		this.description = gameData[1];
		
		String[] edgesData = gameData[2].split(EDGE_SEPERATOR);
		
		for (String edge : edgesData) {
			String[] pair = edge.split(VERTEX_SEPERATOR);
			background.addEdge(new Pair<>(pair[0], pair[1]));
			
			if (isDirected) {
				background.addEdge(new Pair<>(pair[1], pair[0]));
			}
		}
	}
	
	public int calcMaxPlayersNumber() {
		return background.size();
	}
}
