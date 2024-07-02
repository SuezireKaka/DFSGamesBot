package www.disbot.dfsGames.bot.command.impl.attend;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.ThreadMember;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;

@AllArgsConstructor
public class UserNumberChecker {
	private GuildMessageChannel channel;
	
	public int minWithPlayableNumber(int criterion) {
		return Math.min(criterion,
				getMembersList(member -> member.getUser().isBot())
				.size());
	}
	
	private List<Member> getMembersList(Predicate<? super Member> predicate) {
		List<Member> result;
		
		try {
			result = ((ThreadChannel) channel)
					.getThreadMembers()
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
