package www.disbot.dfsGames.api.framework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class Entity {
	public static final int DEFAULT_ID_LENGTH = 4;
	
	public static final String ANONYMOUS_ID = "_".repeat(DEFAULT_ID_LENGTH);
	
	private String id;
}
