package www.disbot.dfsGames.game.search;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import www.disbot.dfsGames.DFSGamesBotApplication;

public class GameFileManager {
	private static final GameFileManager INSTANECE = new GameFileManager();
	
	private final File directory =
			new File(DFSGamesBotApplication.callGameSetup().getGameAddress());
	
	public static GameFileManager getInstance() {
		return INSTANECE;
	}
	
	public List<File> askGameFilesList() {
		return Arrays.stream(directory.listFiles())
				.collect(Collectors.toList());
	}
}
