package www.disbot.dfsGames.game.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.disbot.dfsGames.bot.model.structure.Pair;

public class AdjacencyMapGraph<V> implements Graph<V> {
	private Map<V, List<V>> adjacencyMap = new HashMap<>();
	
	@Override
	public int size() {
		return adjacencyMap.size();
	}

	@Override
	public boolean isAllConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addEdge(Pair<V, V> edge) {
		V key = edge.getFirst();
		if (! adjacencyMap.containsKey(key)) {
			adjacencyMap.put(key, new ArrayList<>());
		}
		adjacencyMap.get(key).add(edge.getSecond());
	}
	
	@Override
	public List<V> getNeighborsList(V vertex) {
		return adjacencyMap.containsKey(vertex)
				? adjacencyMap.get(vertex)
				: new ArrayList<>();
	}

	@Override
	public boolean hasVertex(V vertex) {
		return adjacencyMap.containsKey(vertex) ||
				adjacencyMap.values().stream()
					.anyMatch(list -> list.contains(vertex));
	}
}
