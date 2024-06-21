package www.disbot.dfsGames.bot.parser;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.ContentsParser.ParseType;

@Getter
@RequiredArgsConstructor
public class DiscordContents {
	private List<List<Pair<ParseType, String>>> contents = new ArrayList<>();
	
	@NonNull
	private ContentsParser parser;
	
	public void parse() throws Exception {
		this.contents = parser.parse();
	}
}
