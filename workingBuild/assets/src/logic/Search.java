package logic;
import java.io.File;
import org.json.simple.JSONObject;

public class Search {
	JSONObject obj = new JSONObject();
	public static int retrieveAppList() {
		File currentDir = new File(".");
		System.out.println(currentDir.getAbsolutePath());
		String absolutePath = currentDir.getAbsolutePath();
		String filePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
		File appListFile = new File(filePath + "/appList.json");
		System.out.println(appListFile.getAbsolutePath());
		if (appListFile.exists()) {
			System.out.println("appListFile Found");
		}
		else {
			System.out.println("File not found");
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		retrieveAppList();
	}
}