package www.disbot.dfsGames.game.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.game.visitor.Visitor;

public class AdjacencyMapGraph<V> implements Graph<V> {
	private Map<V, List<V>> adjacencyMap = new HashMap<>();
	private List<V> visitedVerticesList = new ArrayList<>();

	@Override
	public boolean isAllConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addVertex(V vertex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(Pair<V, V> edge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasVisited(V vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<V> getAllNextVerticesOf(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkVisitor(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
