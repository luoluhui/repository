package com.luolh.blog.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.luolh.blog.service.BlogService;

/**
 * service工厂类（创建service实例）
 * @author LuoLH
 * @since 20180601
 */
public class ServiceFactory {
	/**
	 * properties文件路径
	 * */
	private static final String PROPERTIES_PATH = "service.properties";
	
	/**
	 * properties文件路径
	 * */
	private static final String SERVICE_CLASSNAME = "com.luolh.blog.service.BlogService";
	
	/**
	 * properties配置文件
	 * */
	private static Properties properties = null;
	//用于加载配置文件
	static {
		try {
			InputStream inputStream = ServiceFactory.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
			properties = new Properties();
			properties.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 获取BlogService实例
	 * @return BlogService实例
	 */
	public static BlogService getServiceInstance() {
		
		String blogServiceName = properties.getProperty(SERVICE_CLASSNAME);
		
		try {
			Class<?> clazz = Class.forName(blogServiceName);
			return (BlogService) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
