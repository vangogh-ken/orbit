package org.chinacloud.command;


public abstract class CmdTask<T> implements Runnable {
	public abstract T call(T t);
	public abstract void setCommand(Command<T> command);
	public abstract Command<T> getCommand();
	
	@Override
	public void run() {
		getCommand().getCmd().setCommandType(Command.Type.DEFALUT);
		call(getCommand().execute());
	}

}
