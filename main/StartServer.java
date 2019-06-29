package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class StartServer {
	private static Properties settings;
	static{
		settings = new Properties();
		try (FileInputStream fin = new FileInputStream("settings.txt")){
			settings.load(fin); 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		String serverPort = settings.getProperty("ServerPort", "8092");
		String basePath = settings.getProperty("BasePath", "/servlet1");
		
		File file = new File("List");
		if(file.isDirectory()) {
			String[] listFiles = file.list();
			for (String nameFile : listFiles) {
				//System.out.println(basePath + "/" + nameFile);
			}
		}
		else {
			return;
		}
		
	}

}
