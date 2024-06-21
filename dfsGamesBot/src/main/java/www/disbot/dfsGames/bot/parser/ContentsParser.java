package www.disbot.dfsGames.bot.parser;

import java.text.SimpleDateFormat;
import java.util.List;

import www.disbot.dfsGames.bot.model.structure.Pair;

public interface ContentsParser {
	public static final String OMIT_SUFFIX = "(...";
	public static final String OMIT_PREFIX = "...)";
	
	public static final int MAX_ONE_VALUE_LENGTH = 800;
	
	public static final String LIST_SEPERATOR = "\n";
	public static final String SUMMERY_SEPERATOR = "; ";
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public enum ParseType {
		KEY, VAL
	}
	
	public List<List<Pair<ParseType, String>>> parse() throws Exception;
	
	public List<Pair<ParseType, String>> parseLemma();
}
