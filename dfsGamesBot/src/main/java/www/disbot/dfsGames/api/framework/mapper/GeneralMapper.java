package www.disbot.dfsGames.api.framework.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GeneralMapper {
	@Select("SELECT FOUND_ROWS()")
	public int getFoundRows();
	
	@Select("SELECT NEXT_MULTI_PK(#{tName}, ${summonCnt})")
	public String getNextMultiIdConcat(
			@Param("tName") String tName, @Param("summonCnt") int summonCnt);
}
