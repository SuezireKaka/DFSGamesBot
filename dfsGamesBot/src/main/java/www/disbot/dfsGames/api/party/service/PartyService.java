package www.disbot.dfsGames.api.party.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.disbot.dfsGames.api.party.mapper.PartyMapper;
import www.disbot.dfsGames.api.party.model.GroupVO;
import www.disbot.dfsGames.api.party.model.PartyVO;
import www.disbot.dfsGames.api.party.model.RoleVO;
import www.disbot.dfsGames.api.party.model.UserVO;
import www.disbot.dfsGames.api.security.model.SignUpResultDTO;

@Service
public class PartyService {
	@Autowired
	private PartyMapper partyMapper;

	public SignUpResultDTO createUser(UserVO user) {
		SignUpResultDTO result = new SignUpResultDTO();
		
		boolean alreadyRegistered = partyMapper.getUserByDiscordId(user.getDiscordId()) != null;
		
		boolean success = true;
		
		if (! alreadyRegistered) {
			List<RoleVO> jmemoDefaultRolesList = partyMapper.listAllDefaultRolesOf(
					GroupVO.JMEMO_PROXY);
			
			user.setId(partyMapper.getNextMultiIdConcat(PartyVO.SEQUENCE_NAME, 1));
			
			success &= partyMapper.createUser(user);
			
			success &= partyMapper.grantRolesToUser(user, jmemoDefaultRolesList);
		}
		
		result.setSuccess(success);
		
		result.setMsg(alreadyRegistered
				? "이미 등록되어 있으시네요. 앞으로도 잘 부탁드립니다~"
				: success
				? "등록이 완료되었어요! 앞으로 잘 부탁드립니다~"
				: "등록에 실패했어요...");
		
		return result;
	}
	
	
}
