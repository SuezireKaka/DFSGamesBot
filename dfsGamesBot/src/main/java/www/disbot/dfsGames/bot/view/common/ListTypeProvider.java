package www.disbot.dfsGames.bot.view.common;

import java.util.List;

public class ListTypeProvider {
	public static String provideListFieldName(String rawClassName) {
		String firstLetter = rawClassName.substring(0, 1);
		
		String type = rawClassName.replaceFirst(firstLetter, firstLetter.toLowerCase())
				+ List.class.getSimpleName();
		return type;
	}
}
