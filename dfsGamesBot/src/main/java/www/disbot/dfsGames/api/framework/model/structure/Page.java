package www.disbot.dfsGames.api.framework.model.structure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(exclude = {"offset"})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"onePageNum"})
public class Page<T> {
	public static final int ONE_PAGE_NUM = 10;
	
	public static final String PAGE_TERMINOLOGY = "page";
	public static final String PAGE_FORM = "%d / %d";
	
	public static final String CONTENTS_TERMINOLOGY = "contents";
	
	private int nowPage;
	private long offset;
	
	@Setter
	private int totalPage;
	
	List<T> contents;
	
	public Page(int nowPage, List<T> contents) {
		this.nowPage = nowPage;
		this.offset = (nowPage - 1) * ONE_PAGE_NUM;
		this.contents = contents;
	}
	
	public Page<T> addAll(List<T> addList) {
		contents.addAll(addList);
		return this;
	}
	
	public int getOnePageNum() {
		return ONE_PAGE_NUM;
	}
	
	public void applyFoundNumber(int foundNumber) {
		int newTotalPage = foundNumber / ONE_PAGE_NUM + 1;
		setTotalPage(newTotalPage);
		nowPage = Math.min(nowPage, newTotalPage);
	}
	
}
