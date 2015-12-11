package org.chinacloud.command;


public class SimpleTask extends CmdTask<String>{
	private Command<String> command;
	
	public SimpleTask(){
		
	}
	public SimpleTask(Command<String> command){
		this.command = command;
	}
	
	public Command<String> getCommand() {
		return command;
	}

	public void setCommand(Command<String> command) {
		this.command = command;
	}

	@Override
	public String call(String t) {
		System.out.println("this is callback method!");
		try {
			System.out.println("sleeping start");
			Thread.sleep(10*1000);
			System.out.println("sleeping end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}


	
}
