package www.disbot.dfsGames.sys.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class DiscordBotToken {
    @Value("${discord.bot.token}")
    private String discordBotToken;
    
    @Value("${discord.bot.token-prefix}")
    private String tokenPrefix;
    
    @Value("${discord.bot.token-seperator}")
    private String tokenSeperator;
}
