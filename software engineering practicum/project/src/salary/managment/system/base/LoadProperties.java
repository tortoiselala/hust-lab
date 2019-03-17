/**
 * 
 */
package salary.managment.system.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import salary.managment.system.view.PathManager;

/**
 * @author Tortoise 读取配置文件
 */
public class LoadProperties {
	public static void main(String[] args) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		LoadProperties.setIP("localhost");
		LoadProperties.setPort("3306");
		LoadProperties.setRootPass("1546191727");

		System.out.println(LoadProperties.getIP());
		System.out.println(LoadProperties.getPort());
		System.out.println(LoadProperties.getRootPass());
	}


	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getIP() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES));
		return properties.getProperty("ip", "localhost");
		 
	}

	/**
	 * @param iP the iP to set
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void setIP(String ip) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES));
		properties.setProperty("ip", ip);
		System.out.println(PathManager.PATH_MAIN_DATABASE_PROPERTIES);
		properties.store(new FileOutputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES),"database ip, default: localhost");
		
	}

	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getPort() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES));
		return properties.getProperty("port", "3306");
	}

	/**
	 * @param port the port to set
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void setPort(String port) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES));
		properties.setProperty("port", port);
		properties.store(new FileOutputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES),"database port, default: 3306");
	}

	/**
	 * @return the rootPass
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String getRootPass() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES));
		return properties.getProperty("root_pass", "1546191727");
	}

	/**
	 * @param rootPass the rootPass to set
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void setRootPass(String rootPass) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES));
		properties.setProperty("root_pass", rootPass);
		properties.store(new FileOutputStream(PathManager.PATH_MAIN_DATABASE_PROPERTIES),"database root password, default: 1546191727");
	}

}
