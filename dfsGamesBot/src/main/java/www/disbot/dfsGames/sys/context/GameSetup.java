package www.disbot.dfsGames.sys.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class GameSetup {
	@Value("${game.address}")
    private String gameAddress;
}
