package www.disbot.dfsGames.bot;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.ErrorView;

@Slf4j
public class ResponseCarrier {
	public void carryResponseToChannel(MessageChannel msgChannel, View resultView) throws Exception {		
		List<MessageEmbed> resultEmbedList = resultView.close();
		
		for (MessageEmbed embed : resultEmbedList) {
			msgChannel.sendMessage("")
				.setEmbeds(embed)
				.queue();
		}
	}
	
	public void carryErrorToChannel(MessageChannel msgChannel, Exception e, String makerID) {
		String errorMessage = "에러 발생 : " + e.getMessage();
		log.error(errorMessage);
		
		View errorView = new ErrorView(e, makerID);
		
		try {
			errorView.init(Exception.class);
			
			carryResponseToChannel(msgChannel, errorView);
		}
		catch (Exception fatalError) {
			fatalError.printStackTrace();
		}
	}
}
