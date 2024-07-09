package www.disbot.dfsGames.game.graph;

import java.util.List;

import www.disbot.dfsGames.bot.model.structure.Pair;

public interface Graph<V> {
	public int size();
	
	public boolean isAllConnected();
	
	public void addEdge(Pair<V, V> edge);

	public List<V> getNeighborsList(V vertex);
}
