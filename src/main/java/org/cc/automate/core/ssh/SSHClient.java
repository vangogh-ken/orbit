package org.cc.automate.core.ssh;

import java.nio.charset.Charset;

/**
 * SSH协议信息
 * @author Administrator
 *
 */
public class SSHClient {
	private String ip;
	private String username;
	private String password;
	private String charset;
	
	public SSHClient(String ip, String username, String password){
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.charset = Charset.defaultCharset().toString();
	}
	
	public SSHClient(String ip, String username, String password, String charset){
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.charset = charset;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
