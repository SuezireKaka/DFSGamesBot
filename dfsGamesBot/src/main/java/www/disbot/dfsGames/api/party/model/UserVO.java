package www.disbot.dfsGames.api.party.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.dv8tion.jda.api.entities.User;
import www.disbot.dfsGames.DFSGamesBotApplication;

@Getter
@SuperBuilder
@NoArgsConstructor
public class UserVO extends PartyVO implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String discordId;
	
	@Builder.Default
	private List<RoleVO> roleList = new ArrayList<>();
	
	public UserVO adjustName() {
		String userName = DFSGamesBotApplication.callJda()
				.retrieveUserById(this.discordId)
				.map(User::getName)
				.complete();
		
		return UserVO.builder()
				.id(getId())
				.regDt(getRegDt()).uptDt(getUptDt())
				.name(userName)
				.discordId(discordId)
				.build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roleList;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
