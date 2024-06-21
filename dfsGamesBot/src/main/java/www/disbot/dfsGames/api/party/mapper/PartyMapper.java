package www.disbot.dfsGames.api.party.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.disbot.dfsGames.api.framework.mapper.GeneralMapper;
import www.disbot.dfsGames.api.party.model.GroupVO;
import www.disbot.dfsGames.api.party.model.RoleVO;
import www.disbot.dfsGames.api.party.model.UserVO;

@Mapper
public interface PartyMapper extends GeneralMapper {
	public List<RoleVO> listAllAnonymRoles();
	
	public List<RoleVO> listAllDefaultRolesOf(@Param("group") GroupVO group);
	
	
	public UserVO getUserByDiscordId(String discordId);
	

	public boolean createUser(UserVO user);
	
	public boolean grantRolesToUser(@Param("user") UserVO user,
			@Param("rolesList") List<RoleVO> rolesList);
	
}
