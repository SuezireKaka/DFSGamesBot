package www.disbot.dfsGames.sys.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class MakerDiscordID {
	@Value("${discord.bot.maker.discord-user.id}")
    private String makerDiscordID;
}
