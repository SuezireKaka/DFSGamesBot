package www.disbot.dfsGames.game.graph;

import java.util.List;

import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.game.visitor.Visitor;

public interface Graph<V> {
	public boolean isAllConnected();
	
	public void addVertex(V vertex);
	public void addEdge(Pair<V, V> edge);
	
	public boolean hasVisited(V vertex);
	
	public List<V> getAllNextVerticesOf(V vertex);
	
	public void checkVisitor(Visitor v);
}
