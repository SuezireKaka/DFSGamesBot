package www.disbot.dfsGames.game.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

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
	
	private boolean isStarted = false;
	private boolean isFinished = false;
	
	private File gameFile;
	
	private String name;
	private String description;
	private Visitor<String> visitor = new Visitor<>();
	private Graph<String> background = new AdjacencyMapGraph<>();
	
	public GameVO(String name, File gameFile) throws IOException {
		this.name = name;
		this.gameFile = gameFile;
		
		// 게임 csv 참조
		String[] gameData = Files.readString(gameFile.toPath())
				.split(DATA_SEPERATOR);
		
		boolean isDirected = gameData[0].equals(MetaInfoVO.DIRECTED);
		
		this.description = gameData[1];
		
		String[] edgesData = gameData[2].split(EDGE_SEPERATOR);
		
		for (String edge : edgesData) {
			String[] pair = edge.split(VERTEX_SEPERATOR);
			background.addEdge(new Pair<String, String>(pair[0], pair[1]));
			
			if (! isDirected) {
				background.addEdge(new Pair<String, String>(pair[1], pair[0]));
			}
		}
	}
	
	public int calcMaxPlayersNumber() {
		return background.size();
	}

	public void start() {
		this.isStarted = true;
	}
	
	public boolean isVisitable(String vertex) {
		String position = visitor.calcNowPosition();
		
		return position == null // 처음 시작할 때는 어디든 갈 수 있음
			|| ( ! visitor.hasVisited(vertex) // 방문한 적이 없으면서
				&& ( visitor.isComplete() // 연결 클러스터를 다 채웠거나
					// 현재 위치와 연결된 곳이면
					|| background.copyNeighborsList(position).contains(vertex))); 
		
	}
	
	public List<String> visit(String vertex) {
		List<String> result = new ArrayList<>();
		
		result.add(visitor.visit(vertex));

		while (isDeadAlley(visitor.calcNowPosition()) && ! visitor.isComplete()) {
			Pair<String, String> goUpPair = goUp(vertex);
			result.add(goUpPair.getFirst());
			
			vertex = goUpPair.getSecond();
		}
		
		if (visitor.getVisitedVerticesList().containsAll(background.copyVerticesList())) {
			isFinished = true;
		}
		
		return result;
	}
	
	private boolean isDeadAlley(String vertex) {
		List<String> forward = background.copyNeighborsList(vertex);
		
		forward.removeAll(visitor.getVisitedVerticesList());
		
		return forward.size() == 0;
	}
	
	private Pair<String, String> goUp(String vertex) {
		String pastPoint = visitor.goPast();
		
		List<String> sibiling = background.copyNeighborsList(pastPoint);
		
		while (! visitor.isComplete() && ! sibiling.contains(vertex)) {
			pastPoint = visitor.goPast();
			sibiling = background.copyNeighborsList(pastPoint);
		}

		String message = visitor.isComplete()
				? Visitor.COMPLETE_REPORT
				: Visitor.GO_UP_REPORT.formatted(pastPoint);
		
		return new Pair<>(message, pastPoint);
	}
}
