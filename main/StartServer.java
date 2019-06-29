package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

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
	public static void main(String[] args) throws Exception{
		String serverPort = settings.getProperty("ServerPort", "8092");
		String basePath = settings.getProperty("BasePath", "/servlet1");
		Server server = new Server(Integer.parseInt(serverPort));
		
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        
        //Устанавливаем папку запуска приложения как корневую для веб сервера
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new StaticFileServlet("list/main")),"/*");
		
		File file = new File("List");
		System.out.println("файлы для сервера в папке list проекта");
		if(file.exists()&&file.isDirectory()) {
			System.out.println("будут перечислены доступные пути сервера");
			String[] listFiles = file.list();
			for (String nameFile : listFiles) {
				System.out.println(basePath + "/" + nameFile);
				context.addServlet(new ServletHolder(new StaticFileServlet("list/" + nameFile)),basePath + "/" + nameFile);
			}
			
	        HandlerList handlers = new HandlerList();
	        handlers.addHandler(context);
	        server.setHandler(handlers);
	        server.start();
	        server.join();
		}
		else {
			System.out.println("ошибка: папки list нет в папке проекта");
			Thread.currentThread().sleep(5000);
			return;
		}
		

	}

}
