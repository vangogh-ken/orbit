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
	
	public abstract void build();
	
	public abstract CmdDTO getCmd();
	
	public abstract CmdTask<T> getTask();
	
	public Type getCmdType(){
		return getCmd().getCommandType();
	}
	public void setCmdType(Type type){
		getCmd().setCommandType(type);
	}
}