package com.cotton.test;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;


public class UnitTestBase {
	/**
	 * 相对路径应用上下文
	 */
	private ClassPathXmlApplicationContext context;
	
	/**
	 * XML文件的存放路径
	 */
	private String springXmlPath;
	
	public UnitTestBase() {

	}
	
	public UnitTestBase(String springXmlPath){
		this.springXmlPath = springXmlPath;
	}
	/**
	 * 加载XML文件
	 */
	@Before
	public void before(){
		if (StringUtils.isEmpty(springXmlPath)) {
			springXmlPath = "classpath*:applicationContext-*.xml";
		}
		try {
			// 多个xml文件用逗号或者空格符隔开，均可加载
			context = new ClassPathXmlApplicationContext(springXmlPath.split("[,\\s]+"));
			context.start();
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 销毁
	 */
	@After
	public void after(){
		context.destroy();
	}
	
	/**
	 * @param beanId SpringXML文件中的bean的id
	 * @return 该bean的id对应下的实例，默认单例
	 */
	@SuppressWarnings("unchecked")
	protected <T extends Object> T getBean(String beanId){
		try {
			return(T)context.getBean(beanId);
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param clazz 通过类模板获取该类
	 * @return 该类的实例，默认单例
	 */
	protected <T extends Object> T getBean(Class<T> clazz){
		try {
			return context.getBean(clazz);
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
}
