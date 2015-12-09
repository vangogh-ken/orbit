package org.cc.automate.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.cc.automate.core.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *
 */
public class ShellUtil {
	private static Logger LOG = LoggerFactory.getLogger(ShellUtil.class);
	/**
	 * @param shell
	 */
	public static void execShell(String shell) {
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec(shell);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行脚本方式： new String[]{"/bin/sh","-c",shStr}
	 * 
	 * @throws IOException
	 */
	public static List<String> executeSH(String shStr) {
		List<String> strList = new ArrayList<String>();
		try {
			Process process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", shStr }, null, null);
			InputStreamReader ir = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			process.waitFor();
			while ((line = input.readLine()) != null) {
				strList.add(line);
			}
		} catch (IOException e) {
			LOG.error("execute sh command error.  path{}, error {}", shStr, e);
			throw new BusinessException("执行脚本错误");
		} catch (InterruptedException e) {
			LOG.error("execute sh command error while read result. path{}, error {}", shStr, e);
			throw new BusinessException("执行脚本错误");
		}

		return strList;
	}

}
