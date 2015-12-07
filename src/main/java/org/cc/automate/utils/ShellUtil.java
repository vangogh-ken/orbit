package org.cc.automate.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class ShellUtil {
	/**
	 * @param shell
	 */
	public static void execShell(String shell){
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec(shell);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 执行脚本方式： new String[]{"/bin/sh","-c",shStr}
	 * @throws IOException
	 */
	public static List<String> runShell(String shStr) throws Exception {
		List<String> strList = new ArrayList<String>();
		Process process;
		process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c", shStr}, null, null);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		process.waitFor();
		while ((line = input.readLine()) != null){
		    strList.add(line);
		}
		
		return strList;
	}

}
