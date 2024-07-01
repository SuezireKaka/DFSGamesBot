package www.disbot.dfsGames.bot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.utils.FileUpload;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.view.View;
import www.disbot.dfsGames.bot.view.impl.ErrorView;
import www.disbot.dfsGames.bot.view.impl.UnderPreparingView;

@Slf4j
public class ResponseCarrier {
	public void carryResponseToChannel(GuildMessageChannel channel, View resultView, String makerID) throws Exception {
		if (resultView instanceof UnderPreparingView) {
			((UnderPreparingView) resultView).setMakerUsername(makerID);
		}
		
		Pair<List<MessageEmbed>, File> resultPair = resultView.close();
		
		List<MessageEmbed> resultEmbedList = resultPair.getFirst();
		
		File resultAttachFile = resultPair.getSecond();
		
		for (MessageEmbed embed : resultEmbedList) {
			channel.sendMessage("")
				.setEmbeds(embed)
				.queue();
		}
		
		if (resultAttachFile != null) {
			List<FileUpload> attachFilesList = new ArrayList<>();
			
			attachFilesList.add(FileUpload.fromData(resultAttachFile));
			
			channel.sendMessage("")
				.addFiles(attachFilesList)
				.queue();
		}
	}
	
	public void carryErrorToChannel(GuildMessageChannel channel, Exception e, String makerID) {
		String errorMessage = "에러 발생 : " + e.getMessage();
		log.error(errorMessage);
		
		View errorView = new ErrorView(e, makerID);
		
		try {
			errorView.init(Exception.class);
			
			carryResponseToChannel(channel, errorView, makerID);
		}
		catch (Exception fatalError) {
			fatalError.printStackTrace();
		}
	}
}
