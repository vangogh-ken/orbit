package org.cc.automate.core.el;

import java.util.Map;

public class JuelFactory {
	private static JuelFactory instance = new JuelFactory();
	private Resolver resolver;
	private JuelFactory(){
		this.resolver = new Resolver(new Context());
	}
	
	public static JuelFactory juelFactory(){
		if(instance != null){
			return instance;
		}else{
			instance = new JuelFactory();
			return instance;
		}
	}
	
	public void getValue(String sourcePath, String targetPath, Map<String, Object> variables){
		resolver.doneParse(sourcePath, targetPath, variables);
	}
	
}
