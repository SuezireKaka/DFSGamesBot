package www.disbot.dfsGames.game.promise;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import www.disbot.dfsGames.bot.exception.NotMovableException;
import www.disbot.dfsGames.game.model.GameFlowVO;
import www.disbot.dfsGames.game.model.GameVO;
import www.disbot.dfsGames.game.player.PlayerManager;

@Getter
@RequiredArgsConstructor
public class PromisePoint {
	@NonNull
	private PromiseType type;
	@NonNull
	private PlayerManager manager;
	
	@Setter
	private GameVO game = null;

	public GameFlowVO visit(String vertex) throws Exception {
		if (! game.isVisitable(vertex)) {
			throw new NotMovableException(
					game.getVisitor().calcNowPosition(), vertex);
		}
		
		manager.nextTurn();
		List<String> history = game.visit(vertex);
		
		return new GameFlowVO(manager, game, history);
	}
}
