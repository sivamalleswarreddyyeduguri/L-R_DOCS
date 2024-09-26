package dbp.framework.proxy.verify.cmd.service.util;

public class AppUtils {
	private AppUtils(){
	}
	
	public static String buildUrl(String... components) {
		StringBuilder urlBuilder = new StringBuilder();
		
		for (String s : components) {
			urlBuilder.append(s);
		}
		return urlBuilder.toString();
	}
}