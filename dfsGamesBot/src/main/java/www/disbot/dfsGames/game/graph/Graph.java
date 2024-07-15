package www.disbot.dfsGames.game.graph;

import java.util.List;

import www.disbot.dfsGames.bot.model.structure.Pair;

public interface Graph<V> {
	public int size();
	
	public void addEdge(Pair<V, V> edge);
	
	public List<V> copyVerticesList();

	public List<V> copyNeighborsList(V vertex);

	public boolean hasVertex(V vertex);
}
