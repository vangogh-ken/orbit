package org.chinacloud.command;

import org.chinacloud.command.util.CmdUtils;

/**
 * @author Administrator
 *
 * @param <T> 返回值
 */
public abstract class Command<T> {
	public enum Type{
		DEFALUT, ASYNC
	}
	
	@SuppressWarnings("unchecked")
	public T execute(){
		build();
		if(getCmd().getCommandType() == Command.Type.DEFALUT){
			return (T) CmdUtils.execute(getCmd().getCommand());
		}else{
			new Thread(getTask()).start();
		}
		return null;
	}
	
	abstract void build();
	
	abstract CmdDTO getCmd();
	
	abstract CmdTask<T> getTask();
	
	Type getCmdType(){
		return getCmd().getCommandType();
	}
	void setCmdType(Type type){
		getCmd().setCommandType(type);
	}
}