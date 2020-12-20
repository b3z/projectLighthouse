package app.Model;

public class CheckOS {
	
	/**
	 * detect OS
	 * @return
	 */
	public static String os() {
		return System.getProperty("os.name");
	}
	
	
	/**
	 * We need this to handle the win issue.
	 * The win issue is the issue that UNIX and WIN are using different filesystem. So with that we know which one the App has to use.
	 * 
	 * The win issue does not affect us all the time. Sometimes it just works fine with wrong slashes. But sometimes we get exceptions so this is to avoid an error source.
	 * 
	 * @return boolean true = windows
	 * e.g. FTPHandling or DB path
	 */
	public static boolean isWin() {
		if(System.getProperty("os.name").indexOf("Windows")!=-1)
			return true;
		else
			return false;
	}
	
}