package www.disbot.dfsGames.bot.view;

import java.awt.Color;
import java.io.File;
import java.util.List;

import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.dfsGames.bot.model.structure.Pair;

public interface View {
	public static final String ITALIC = "*%s*";
	public static final String BOLD = "**%s**";
	public static final String UNDERLINE = "__%s__";
	public static final String CANCEL = "~~%s~~";
	
	public static final String SPACE = " ";
	
	public static final String MENTION_FORMAT = "<@%s>";
	
	public static final String RESULT_TITLE_SUFFIX = "의 결과물";
	
	public static final Color SUCCESS_COLOR = new Color(52, 200, 31);
	
	public <T> void init(Class<T> cls) throws Exception;
	
	public Pair<List<MessageEmbed>, File> close() throws Exception;
}
