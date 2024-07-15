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
	public void addEdge(Pair<V, V> edge) {
		V key = edge.getFirst();
		if (! adjacencyMap.containsKey(key)) {
			adjacencyMap.put(key, new ArrayList<>());
		}
		adjacencyMap.get(key).add(edge.getSecond());
	}
	
	@Override
	public List<V> copyVerticesList() {
		List<V> result = new ArrayList<>();
		
		for (V v : adjacencyMap.keySet()) {
			result.add(v);
		}
		return result;
	}
	
	@Override
	public List<V> copyNeighborsList(V vertex) {
		List<V> result = new ArrayList<>();
		
		if (adjacencyMap.containsKey(vertex)) {
			result.addAll(adjacencyMap.get(vertex));
		}
		
		return result;
	}

	@Override
	public boolean hasVertex(V vertex) {
		return adjacencyMap.containsKey(vertex) ||
				adjacencyMap.values().stream()
					.anyMatch(list -> list.contains(vertex));
	}
}
