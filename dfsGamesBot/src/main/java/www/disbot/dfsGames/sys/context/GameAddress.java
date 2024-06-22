package www.disbot.dfsGames.sys.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class GameAddress {
	@Value("${game.address}")
    private String gameAddress;
}
