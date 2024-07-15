package www.disbot.dfsGames.game.visitor;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Visitor<V> {
	public static final String SUCCESS_REPORT = "%s에 도착했습니다.\n";
	
	public static final String NEIBHOR_OF = " \"%s\"에 인접한";
	public static final String NOT_VISITED = "이전에 방문한 적 없는";
	
	public static final String GO_UP_REPORT = "더이상 진행이 불가능해 %s로 올라왔습니다.\n";
	
	public static final String COMPLETE_REPORT = "클러스터를 완성했습니다.\n";
	
	public static final String ENDING_REPORT =
			"모든 점을 방문하여 게임이 종료되었습니다."
			+ "\n수고하셨습니다~";
	
	private int nowPosition = -1;
	private List<V> visitedVerticesList = new ArrayList<>();
	
	@Setter
	private boolean isComplete = false;
	
	public boolean hasVisited(V vertex) {
		return visitedVerticesList.contains(vertex);
	}
	
	public V calcNowPosition() {
		return nowPosition < 0 ? null : visitedVerticesList.get(nowPosition);
	}

	public String visit(V vertex) {
		visitedVerticesList.add(vertex);
		nowPosition = visitedVerticesList.size() - 1;
		
		return SUCCESS_REPORT.formatted(vertex);
	}

	public String visitCondition() {
		String result = NOT_VISITED;
		
		if (! isComplete) {
			result += NEIBHOR_OF.formatted(visitedVerticesList.get(nowPosition));
		}
		
		isComplete = false;
		
		return result;
	}

	public V goPast() {
		nowPosition -= 1;
		
		if (nowPosition < 0) {
			isComplete = true;
			return null;
		}
		
		return visitedVerticesList.get(nowPosition);
	}
}
