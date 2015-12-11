package org.chinacloud.command;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleCmd extends Command<String>{
	private static Logger LOG = LoggerFactory.getLogger(SimpleCmd.class);
	private CmdDTO cmd;
	
	public SimpleCmd(CmdDTO cmd){
		this.cmd = cmd;
	}
	
	@Override
 	void build() {
		StringBuilder text = new StringBuilder();
		text.append(cmd.getAbsolutePath());
		if(cmd.getParams() != null && !cmd.getParams().isEmpty()){
			for(Entry<String, Object> entry : cmd.getParams().entrySet()){
				text.append(" --" + entry.getKey() + "=" + entry.getValue());
			}
		}
		cmd.setCommand(text.toString());
		LOG.info("build command text");
	}
	
	public CmdDTO getCmd() {    
		return cmd;
	}

	public void setCmd(CmdDTO cmd) {
		this.cmd = cmd;
	}

	@Override
	CmdTask<String> getTask() {
		return new SimpleTask(this);
	}
	
}
