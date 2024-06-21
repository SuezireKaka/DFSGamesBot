package www.disbot.dfsGames.api.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import www.disbot.dfsGames.api.framework.model.Entity;
import www.disbot.dfsGames.api.party.mapper.PartyMapper;
import www.disbot.dfsGames.api.party.model.RoleVO;
import www.disbot.dfsGames.api.party.model.UserVO;

@Component
@RequiredArgsConstructor
public class TokenExaminater {
	private final PartyMapper partyMapper;
	
	@Value("${discord.bot.token-prefix}")
	private String tokenPrefix;
	
	@Value("${discord.bot.token-seperator}")
	private String tokenSeperator;
	
	@Value("${discord.bot.token}")
	private String answerToken;
	
	public String resolveToken(HttpServletRequest request) {
        String requestHeader = request.getHeader("x-auth-token");
        String token = null;

        if (requestHeader != null
        		&& requestHeader.startsWith(tokenPrefix)
        		&& requestHeader.split(tokenPrefix).length == 2) {
        	
            token = requestHeader.substring(tokenPrefix.length());
        }
        return token; 
	}

	public boolean validateToken(String token) {
		String[] splitedToken = token.split(tokenSeperator);
		return splitedToken[0].equals(answerToken);
	}

	public Authentication getAuthentication(String token) {
		String discordId = getUserID(token);
		
		UserVO partyDetails = partyMapper.getUserByDiscordId(discordId);
		
		// 여기까지 왔으면 미등록 유저라고 해도 Anonym View는 줘야 함
		if (partyDetails == null) {
			partyDetails = grantAnonymToken(partyDetails, discordId);
		}
		
		return new UsernamePasswordAuthenticationToken(
				partyDetails, "", partyDetails.getAuthorities());
	}

	private UserVO grantAnonymToken(UserVO partyDetails, String discordId) {
		List<RoleVO> anonymRoleList = partyMapper.listAllAnonymRoles();
		
		UserVO anonymUser = UserVO.builder()
				.id(Entity.ANONYMOUS_ID)
				.name("")
				.discordId(discordId)
				.roleList(anonymRoleList)
				.build();
		
		return anonymUser.adjustName();
	}

	private String getUserID(String token) {
		return token.split(tokenSeperator)[1];
	}

}