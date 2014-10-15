package books;//Change for C4

public interface Action {
	
	public static String SUCCESS = "success";
	public static String NONE = "none";
	public static String ERROR = "error";
	public static String INPUT = "input";
	public static String LOGIN = "login";
	public static String username = "username";
	
	public String execute() throws Exception;
}
