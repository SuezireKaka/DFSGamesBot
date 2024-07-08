package www.disbot.dfsGames.bot.command.impl.attend;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.ThreadMember;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import www.disbot.dfsGames.bot.exception.TooManyPeopleRequiredException;

@AllArgsConstructor
public class UserNumberChecker {
	private GuildMessageChannel channel;
	
	public boolean isPlayable(int criterion) throws Exception {
		int minNum = getMembersList(member -> ! member.getUser().isBot())
				.size();
		
		if (criterion > minNum) {
			throw new TooManyPeopleRequiredException(criterion, minNum);
		}
			
		return true;
	}
	
	private List<Member> getMembersList(Predicate<? super Member> predicate) {
		List<Member> result;
		
		try {
			result = ((ThreadChannel) channel)
					.retrieveThreadMembers()
					.stream()
					.map(ThreadMember::getMember)
					.collect(Collectors.toList());
		}
		catch (ClassCastException e) {
			result = channel.getGuild().getMembers();
		}
		
		return result.stream()
				.filter(predicate)
				.collect(Collectors.toList());
	}
}
