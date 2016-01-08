package org.cc.automate.core.ssh;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.cc.automate.core.Constant;

public class SSHFactory {
	private static SSHFactory instance = new SSHFactory();
	private RemoteSFTP remoteSFTP;
	private SSHFactory(){
		this.remoteSFTP = new RemoteSFTP();
	}
	
	public static SSHFactory sSHFactory(){
		if(instance != null){
			return instance;
		}else{
			instance = new SSHFactory();
			return instance;
		}
	}
	
	
	public void dealConfigWithSSL(){
		if(Constant.isRemoteExecute){
			remoteSFTP.uploadWithSSL(Constant.deployServerSSHClient, new File(Constant.configTargetSLSPath), Constant.deployConfigPath);
		}else{
			try {
				FileUtils.copyFile(new File(Constant.configTargetSLSPath), new File(Constant.deployConfigPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void dealNohupWithSSL(){
		
	}
}
