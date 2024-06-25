package www.disbot.dfsGames.game.search;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import www.disbot.dfsGames.DFSGamesBotApplication;

public class GameFileManager {
	private static final GameFileManager INSTANECE = new GameFileManager();
	
	public static GameFileManager getInstance() {
		return INSTANECE;
	}
	
	private final File directory =
			new File(DFSGamesBotApplication.callGameSetup().getGameAddress());
	
	public final String GAME_EXTENSION = ".csv";
	
	public List<File> findGameFilesList() {
		return Arrays.stream(directory.listFiles())
				.filter(file -> file.getName().endsWith(GAME_EXTENSION))
				.collect(Collectors.toList());
	}
	
	public File findGameFileWithName(String name) {
		List<File> matchingGamesList = findGameFilesList().stream()
				.filter(file -> file.getName().startsWith(name))
				.collect(Collectors.toList());
		
		if (matchingGamesList.size() == 0) {
			return null;
		}
		
		return matchingGamesList.get(0);
	}
}
