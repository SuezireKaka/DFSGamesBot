package www.disbot.dfsGames.api.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 회원가입 결과 정보 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResultDTO {
	private boolean success;
	private String msg;
}