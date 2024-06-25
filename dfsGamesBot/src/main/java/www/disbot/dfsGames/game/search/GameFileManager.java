package www.disbot.dfsGames.game.search;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import www.disbot.dfsGames.DFSGamesBotApplication;
import www.disbot.dfsGames.bot.exception.NoGameFoundException;
import www.disbot.dfsGames.game.model.GameDTO;
import www.disbot.dfsGames.game.model.GameVO;

public class GameFileManager {
	private static final GameFileManager INSTANECE = new GameFileManager();
	
	public static GameFileManager getInstance() {
		return INSTANECE;
	}
	
	private final File directory =
			new File(DFSGamesBotApplication.callGameSetup().getGameAddress());
	
	public final String GAME_EXTENSION = ".csv";
	public final String DATA_SEPERATOR = "\n\n";
	
	public List<File> findGameFilesList() {
		return Arrays.stream(directory.listFiles())
				.filter(file -> file.getName().endsWith(GAME_EXTENSION))
				.collect(Collectors.toList());
	}
	
	public GameDTO extractGame(String name) throws Exception {
		File gameFile = findGameFileWithName(name);
		
		String[] contents = Files.readString(gameFile.toPath()).split(DATA_SEPERATOR);
		
		return new GameDTO(contents[0].equals(GameDTO.DIRECTED)
				, name, contents[1]);
	}
	
	private File findGameFileWithName(String name) throws Exception {
		List<File> matchingGamesList = findGameFilesList().stream()
				.filter(file -> file.getName().startsWith(name))
				.collect(Collectors.toList());
		
		if (matchingGamesList.size() == 0) {
			throw new NoGameFoundException(name);
		}
		
		return matchingGamesList.get(0);
	}
}
