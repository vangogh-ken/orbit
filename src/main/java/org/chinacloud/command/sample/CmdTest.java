package org.chinacloud.command.sample;

import org.chinacloud.command.CmdDTO;
import org.chinacloud.command.Command;
import org.chinacloud.command.SimpleCmd;

/**
 * 
 * 继承SimpleCmd 重写build方法
 * 
 * @author Administrator
 *
 */
public class CmdTest {
	public static void main(String[] args) {
		SimpleCmd SimpleCmd = new SimpleCmd(new CmdDTO(Command.Type.ASYNC, "DSFDSA"));
		SimpleCmd.execute();
		System.out.println(1);
	}
}
