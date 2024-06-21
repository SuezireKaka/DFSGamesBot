package www.disbot.dfsGames.bot.command;

import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import www.disbot.dfsGames.api.framework.model.structure.Page;
import www.disbot.dfsGames.api.security.model.EntryPointErrorResponse;
import www.disbot.dfsGames.bot.command.api.RequestStrategy;
import www.disbot.dfsGames.bot.exception.StrangeResponseException;

public abstract class ApiCommand implements Command {
	public static final String API_SUFFIX = "/";
	public static final String PATH_REFER_OPEN = "{";
	public static final String PATH_REFER_CLOSE = "}";
	
	public static final String INT_VARIABLE = API_SUFFIX + "%d";
	public static final String STR_VARIABLE = API_SUFFIX + "%s";
	
	public static final String MEMO = API_SUFFIX + "memo";
	public static final String PARTY = API_SUFFIX + "party";
	
	public static final String LIST_ALL = API_SUFFIX + "listAll";
	public static final String PATH_PAGE = API_SUFFIX + PATH_REFER_OPEN + "page" + PATH_REFER_CLOSE;
	
	public static final String BY_ID = API_SUFFIX + "byId";
	public static final String PATH_ID = API_SUFFIX + PATH_REFER_OPEN + "id" + PATH_REFER_CLOSE;
	
	public static final String CREATE = API_SUFFIX + "create";
	
	@Setter
	private RequestStrategy requestStrategy;
	
	public ApiCommand(RequestStrategy requestStrategy) {
		this.requestStrategy = requestStrategy;
	}
	
	protected <B, T> T requestTo(String urlTail, HttpMethod method, B body,
			Class<T> resultMapClass) throws Exception {
		String responseString = requestStrategy.requestTo(urlTail, method, body);
		
		ObjectMapper mapper = new ObjectMapper();
		
		T result = null;
		
		try {
			result = mapper.readValue(responseString, resultMapClass);
		}
		catch (JsonProcessingException jpe) {
			extractErrorMsg(resultMapClass, responseString, mapper, jpe);
		}
		
		return result;
	}
	
	protected <B, T> Page<T> requestTo(String urlTail, HttpMethod method, B body,
			Class<T> resultMapClass, TypeReference<Page<T>> ref) throws Exception {
		String responseString = requestStrategy.requestTo(urlTail, method, body);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Page<T> result = null;
		
		try {
			result = mapper.readValue(responseString, ref);
		}
		catch (JsonProcessingException jpe) {
			extractErrorMsg(resultMapClass, responseString, mapper, jpe);	
		}
		
		return result;
	}
	
	private <T> void extractErrorMsg(Class<T> resultMapClass, String responseString, ObjectMapper mapper,
			JsonProcessingException jpe) throws Exception, StrangeResponseException {
		try {
			jpe.printStackTrace();
			EntryPointErrorResponse error = mapper.readValue(responseString, EntryPointErrorResponse.class);
			throw new Exception(error.getMsg());
		}
		catch (JsonProcessingException fatalJpe) {
			throw new StrangeResponseException(resultMapClass.getName());
		}
	}
}
