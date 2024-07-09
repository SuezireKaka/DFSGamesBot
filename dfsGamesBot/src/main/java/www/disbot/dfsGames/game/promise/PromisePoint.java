package www.disbot.dfsGames.game.promise;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import www.disbot.dfsGames.game.model.GameVO;
import www.disbot.dfsGames.game.player.PlayerManager;

@Getter
@RequiredArgsConstructor
public class PromisePoint {
	@NonNull private PromiseType type;
	@NonNull private PlayerManager manager;
	
	@Setter private GameVO game = null;
}
