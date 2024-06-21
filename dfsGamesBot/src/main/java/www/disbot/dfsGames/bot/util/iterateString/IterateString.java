package www.disbot.dfsGames.bot.util.iterateString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;

@Getter
public class IterateString implements Iterable<String> {
	private String fullContents;

	private IterateStrategy strategy;
	
	private List<String> seperatedContents;
	
	public IterateString(String fullContents, IterateStrategy strategy) {
		this.fullContents = fullContents;
		this.strategy = strategy;
		
		this.seperatedContents = new ArrayList<>();
		
		seperatedContents.addAll(strategy.parseToIterate(fullContents));
	}

	@Override
	public Iterator<String> iterator() {
		return seperatedContents.iterator();
	}
}
