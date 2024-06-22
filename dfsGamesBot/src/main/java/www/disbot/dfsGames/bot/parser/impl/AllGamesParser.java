package www.disbot.dfsGames.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.dfsGames.api.framework.model.structure.Page;
import www.disbot.dfsGames.bot.model.structure.Pair;
import www.disbot.dfsGames.bot.parser.DiscordParser;

@AllArgsConstructor
public class AllGamesParser extends DiscordParser {
	private Page<String> dto;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();

		String pageKeyString = Page.PAGE_TERMINOLOGY;
		Pair<ParseType, String> key = new Pair<>(ParseType.KEY, pageKeyString);
		
		String pageValString = Page.PAGE_FORM.formatted(dto.getNowPage(), dto.getTotalPage());
		Pair<ParseType, String> val = new Pair<>(ParseType.VAL, pageValString);
		
		lemma.add(key);
		lemma.add(val);
		
		String keyString = Page.CONTENTS_TERMINOLOGY;
		key = new Pair<>(ParseType.KEY, keyString);
		
		List<String> contents = dto.getContents();
		
		String valString = contents.stream()
				.reduce("", (f, s) -> {
					return f.length() > 1
							? f + LIST_SEPERATOR + s
							: s;
				});
		
		val = new Pair<>(ParseType.VAL, valString);
		
		lemma.add(key);
		lemma.add(val);

		return lemma;
	}
}
