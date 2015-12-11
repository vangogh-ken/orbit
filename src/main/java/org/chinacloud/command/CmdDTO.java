package org.chinacloud.command;

import java.util.Map;

/**
 * 命令基本信息
 * @author Administrator
 *
 */
public class CmdDTO {
	private Command.Type commandType;
	private String command;
	private String absolutePath;
	private Map<String, Object> params;
	
	public CmdDTO(Command.Type commandType, String absolutePath, Map<String, Object> params){
		this.commandType = commandType;
		this.absolutePath = absolutePath;
		this.params = params;
	}
	
	public CmdDTO(Command.Type commandType, String absolutePath){
		this.commandType = commandType;
		this.absolutePath = absolutePath;
	}
	
	public CmdDTO(String absolutePath){
		this.commandType = Command.Type.DEFALUT;
		this.absolutePath = absolutePath;
	}
	
	public CmdDTO(String absolutePath, Map<String, Object> params){
		this.commandType = Command.Type.DEFALUT;
		this.absolutePath = absolutePath;
		this.params = params;
	}
	
	public Command.Type getCommandType() {
		return commandType;
	}
	public void setCommandType(Command.Type commandType) {
		this.commandType = commandType;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	
}
