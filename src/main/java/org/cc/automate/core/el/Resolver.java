package org.cc.automate.core.el;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.cc.automate.core.BusinessException;
import org.cc.orbit.core.juel.ExpressionFactoryImpl;
import org.cc.orbit.core.juel.javax.el.ExpressionFactory;
import org.cc.orbit.core.juel.javax.el.ValueExpression;
import org.cc.orbit.core.juel.util.SimpleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resolver {
	private static Logger LOG = LoggerFactory.getLogger(Resolver.class);
	private Context context;
	
	public Resolver(Context context){
		this.context = context;
	}
	
	public void doneParse(String sourcePath, String targetPath, Map<String, Object> variables){
		Map<String, Object> map = doneConvert(sourcePath, variables);
		String tempPath = (String)map.get("target");
		 ExpressionFactory factory = new ExpressionFactoryImpl(System.getProperties());
         SimpleContext simpleContext = new SimpleContext();
         for(Entry<String, Object> entry : this.context.getVariables().entrySet()){
        	 simpleContext.setVariable(entry.getKey(), factory.createValueExpression(entry.getValue(), Object.class));
         }
		 try {
			ValueExpression expr = factory.createValueExpression(simpleContext, FileUtils.readFileToString(new File(tempPath), "UTF-8"), Object.class);
			FileUtils.writeStringToFile(new File(targetPath), expr.getValue(simpleContext).toString());;
		 } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> doneConvert(String path, Map<String, Object> variables){
		this.context.setVariables(variables);
		Resolver resolver = new Resolver(context);
		try {
			File sourceFile = new File(path);
			List<String> lines = resolver.parseForeach(FileUtils.readLines(sourceFile, "UTF-8"));
			StringBuilder text = new StringBuilder();
			for(String s : lines){
				text.append(s + "\r\n");
			}
			String targetPath = sourceFile.getPath().substring(0, sourceFile.getPath().lastIndexOf(File.separator)) + File.separator + new Date().getTime() + sourceFile.getName();

			FileUtils.writeStringToFile(new File(targetPath), text.toString());
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("context", this.context);
			map.put("target", targetPath);
			
			return map;
		} catch (IOException e) {
			throw new BusinessException("could not read target file, path : " + path);
		}
	}
	
	public List<String> parseForeach(List<String> lines){
		List<String> list = new ArrayList<String>();
		List<String> forEachLines = new ArrayList<String>();
		Map<String, String> forEachAttr = new HashMap<String, String>();
		boolean flag = true;//标记是否开头
		for(int i=0, len=lines.size(); i<len; i++){
			String line = lines.get(i);
			Matcher matcher = getForeachStartMatcher(line);
			if(matcher.find()){
				if(flag){
					if(matcher.groupCount() != 1){
						throw new BusinessException("foreach tag parse failed, line number " + i);
					}else{
						forEachLines = new ArrayList<String>();//多个foreach标签的时候需要把之前的去除掉
						String text = matcher.group(0);
						forEachAttr = getForeachAttribute(text);
						flag = false;
					}
				}else{
					throw new BusinessException("foreach tag parse failed, line number " + i);
				}
			}else{
				matcher = getForeachEndMatcher(line);
				if(matcher.find()){
					if(!flag){
						List<?> items = (List<?>)context.getVariable(forEachAttr.get("items"));
						if(items == null || items.isEmpty()){//如果参数为空或者数据为空，则
							
							LOG.warn("foreach tag parse failed, can't find variable in context: " + forEachAttr.get("items") + ", line number " + i);
							flag = true;
							continue;//直接跳过,继续进行解析
							//return list;//直接空返回
							//throw new BusinessException("foreach tag parse failed, can't find variable in context: " + forEachAttr.get("items") + ", line number " + i);
						}else{
							for(int j=0, size=items.size(); j<size; j++){
								for(String forEachLine : forEachLines){
									list.add(setVar(forEachAttr.get("var"), forEachAttr.get("var") + j, forEachLine));
								}
								
								context.setVariable(forEachAttr.get("var") + j, items.get(j));
							}
						}
						flag = true;
					}else{
						throw new BusinessException("foreach tag parse failed, line number" + i);
					}
				}else{
					if(!flag){
						forEachLines.add(line);
					}else{
						list.add(line);
					}
				}
			}
		}
		return list;
	}
	
	public Matcher getEvalMatcher(String text){
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher;
	}
	
	public String setVar(String var, String varReplacement, String text){
		Pattern pattern = Pattern.compile("\\$\\{" + var + ".(.+?)\\}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){
			text = text.substring(0, text.indexOf("${" + var + ".")) + "${" + varReplacement + text.substring(text.indexOf("${" + var + ".") + 2 + var.length());
		}
		return text;
	}
	
	/**
	 * 规则：<foreach>标签只能分别在一行
	 * @param text
	 * @return
	 */
	public Matcher getForeachStartMatcher(String text){
		Pattern pattern = Pattern.compile("\\<foreach(.+?)\\>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher;
	}
	
	public Matcher getForeachEndMatcher(String text){
		Pattern pattern = Pattern.compile("\\</foreach\\>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher;
	}
	
	public Map<String, String> getForeachAttribute(String text){
		String itemsPattern = "\\<foreach(.+?)items\\=\"(.+?)\"(.+?)\\>";
		String varPattern = "\\<foreach(.+?)var\\=\"(.+?)\"\\>";
		Pattern pattern = Pattern.compile(itemsPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		matcher.find();
		String t = matcher.group(0);
		String items = t.substring(t.indexOf("items=") + 7, t.indexOf("\" "));
		
		pattern = Pattern.compile(varPattern, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(text);
		matcher.find();
		t = matcher.group(0);
		String var = t.substring(t.indexOf("var=") + 5, t.lastIndexOf("\""));
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("items", items);
		map.put("var", var);
		return map;
	}
	
}
