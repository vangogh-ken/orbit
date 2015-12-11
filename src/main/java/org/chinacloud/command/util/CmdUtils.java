package org.chinacloud.command.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmdUtils {
	private static Logger LOG = LoggerFactory.getLogger(CmdUtils.class);
	public static String execute(String command) {
		StringBuilder text = new StringBuilder();
		try {
			Process process = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", command}, null, null);
			InputStreamReader ir = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			process.waitFor();
			while ((line = input.readLine()) != null) {
				text.append(line + "\r\n");
			}
		} catch (IOException e) {
			LOG.error("execute sh command error. path {}, error {}", command, e);
			//throw new BusinessException("执行脚本错误");
			return "could not find any command error";
		} catch (InterruptedException e) {
			LOG.error("execute sh command error while read result. path {}, error {}", command, e);
			//throw new BusinessException("执行脚本错误");
			return "execute command error";
		}

		return text.toString();
	}
}
