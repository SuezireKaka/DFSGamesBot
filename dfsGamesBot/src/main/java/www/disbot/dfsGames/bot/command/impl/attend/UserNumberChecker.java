package www.disbot.dfsGames.bot.command.impl.attend;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.ThreadMember;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import www.disbot.dfsGames.bot.exception.TooLessPeopleRequiredException;
import www.disbot.dfsGames.bot.exception.TooManyPeopleRequiredException;
import www.disbot.dfsGames.bot.exception.TooManyPlayersRequiredException;
import www.disbot.dfsGames.game.model.GameVO;

@AllArgsConstructor
public class UserNumberChecker {
	public static final int MINIMUM_PLAYER_NUMBER = 1;
	
	private GuildMessageChannel channel;
	
	public boolean isPlayable(GameVO game, int criterion) throws Exception {
		int maxNum = game.calcMaxPlayersNumber();
		
		if (criterion > maxNum) {
			throw new TooManyPlayersRequiredException(game, maxNum, criterion);
		}
		
		return isPlayable(criterion);
	}
	
	public boolean isPlayable(int criterion) throws Exception {
		int maxNum = calcMemberNumber();
		
		if (criterion > maxNum) {
			throw new TooManyPeopleRequiredException(criterion, maxNum);
		}
		
		if (criterion < MINIMUM_PLAYER_NUMBER) {
			throw new TooLessPeopleRequiredException();
		}
		
		return true;
	}

	private int calcMemberNumber() {
		int minNum = getMembersList(member -> ! member.getUser().isBot())
				.size();
		return minNum;
	}
	
	private List<Member> getMembersList(Predicate<? super Member> predicate) {
		Stream<Member> memberStream;
		
		try {
			memberStream = ((ThreadChannel) channel)
					.retrieveThreadMembers()
					.stream()
					.map(ThreadMember::getMember);
		}
		catch (ClassCastException e) {
			memberStream = channel.getGuild().getMembers().stream();
		}
		
		return memberStream
				.filter(predicate)
				.collect(Collectors.toList());
	}
}
