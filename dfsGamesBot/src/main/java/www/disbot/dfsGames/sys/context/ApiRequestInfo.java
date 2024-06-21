package www.disbot.dfsGames.sys.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ApiRequestInfo {
	@Value("${api.request.source.host}")
    private String sourceHost;
    
    @Value("${api.request.source.port}")
    private int sourcePort;
    
    @Value("${api.request.goal.host}")
    private String goalHost;
    
    @Value("${api.request.goal.port}")
    private int goalPort;
}
