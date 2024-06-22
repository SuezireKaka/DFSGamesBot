package www.disbot.dfsGames.bot.command.api;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.User;
import www.disbot.dfsGames.DFSGamesBotApplication;

@RequiredArgsConstructor
public class WebClientRequestStrategy implements RequestStrategy {
	private ContextHandler handler =
			new ContextHandler(DFSGamesBotApplication.callContext());
	
	private User user;

	public void save(User user) {
		this.user = user;
	}

	@Override
	public <B> String requestTo(String urlTail, HttpMethod method, B body) {
		WebClient webClient = DFSGamesBotApplication.callWebClient();
		
		String userId = user == null ? "" : user.getId();
		
		String result = "";
		
		if (method.equals(HttpMethod.GET)) {
			result = webClient.get()
					.uri(urlTail)
					.header("x-auth-token", saltToken(userId))
					.retrieve()
					.bodyToMono(String.class)
					.block();
		}
		
		if (method.equals(HttpMethod.POST)) {
			result = webClient.post()
					.uri(urlTail)
					.header("x-auth-token", saltToken(userId))
					.bodyValue(body)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		}
		
		return result;
	}

	private String saltToken(String userId) {
		
		return handler.getTokenPrefix()
				+ handler.getAnswerToken()
				+ handler.getTokenSeperator()
				+ userId;
	}

}
