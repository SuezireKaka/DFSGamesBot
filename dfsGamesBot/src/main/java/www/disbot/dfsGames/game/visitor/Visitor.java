package www.disbot.dfsGames.game.visitor;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Visitor<V> {
	private V nowPosition;
	private List<V> visitedVerticesList = new ArrayList<>();
	
	public boolean hasVisited(V vertex) {
		return visitedVerticesList.contains(vertex);
	}
}
