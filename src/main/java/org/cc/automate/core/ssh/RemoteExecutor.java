package org.cc.automate.core.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.cc.automate.core.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 远程执行命令
 * 
 * @author Administrator
 *
 */
public class RemoteExecutor {
	private static Logger LOG = LoggerFactory.getLogger(RemoteExecutor.class);
	private static final int TIME_OUT = 1000 * 5;// 5s超时时间
	private Connection conn;
	private String charset;

	/**
	 * 登录
	 * @param client
	 * @return
	 */
	public boolean doLogin(SSHClient client) {
		conn = new Connection(client.getIp());
		this.charset = client.getCharset();
		try {
			conn.connect();
			return conn.authenticateWithPassword(client.getUsername(), client.getPassword());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 执行命令
	 * @param client
	 * @param cmds
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> execute(SSHClient client, String cmds) throws Exception {
		InputStream stdOut = null;
		InputStream stdErr = null;
		String successText = "";
		String errorText = "";
		int resultCode = -1;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (doLogin(client)) {
				Session session = conn.openSession();
				session.execCommand(cmds);

				stdOut = new StreamGobbler(session.getStdout());
				successText = toString(stdOut, charset);

				stdErr = new StreamGobbler(session.getStderr());
				errorText = toString(stdErr, charset);

				session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
				resultCode = session.getExitStatus();// 脚本退出之后的最终状态

				result.put("successText", successText);
				result.put("errorText", errorText);
				result.put("resultCode", resultCode);
				
				LOG.info("执行命令: {}", cmds);
				LOG.info("返回状态: {}", resultCode);
				LOG.info("输出内容: {}", successText);
				LOG.info("错误内容: {}", errorText);
				return result;
			} else {
				throw new BusinessException("登录远程机器失败");
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
			IOUtils.closeQuietly(stdOut);
			IOUtils.closeQuietly(stdErr);
		}
	}

	private String toString(InputStream in, String charset) throws Exception {
		List<String> list = IOUtils.readLines(in, charset);
		StringBuilder text = new StringBuilder();
		if (list != null && !list.isEmpty()) {
			for (String s : list) {
				text.append(s + "\r\n");
			}
		}

		return text.toString();
	}
}
