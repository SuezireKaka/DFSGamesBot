package www.disbot.dfsGames.api.party.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class GroupVO extends PartyVO {
	public static final GroupVO JMEMO_PROXY = GroupVO.builder().id("0000").build();
	
	private boolean isOpen;
	private String introduce;
}
