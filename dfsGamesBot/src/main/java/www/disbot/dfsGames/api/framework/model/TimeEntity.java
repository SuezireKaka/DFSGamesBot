package www.disbot.dfsGames.api.framework.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class TimeEntity extends Entity {
	private Date regDt;
	private Date uptDt;
}
