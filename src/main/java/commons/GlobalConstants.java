package commons;

import java.io.File;

public class GlobalConstants {
	public static final long LONG_TIMEOUT = 30; 
	public static final long SHORT_TIMEOUT = 5;
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	
	public static final String BROWSER_USER_NAME = "buitung_69zKnk";
	public static final String BROWSER_AUTOMATE_KEY = "pUiU2EYvzXtVCgyRs2fR";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_USER_NAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
}