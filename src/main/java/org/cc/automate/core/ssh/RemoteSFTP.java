package org.cc.automate.core.ssh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;

/**
 * 使用SFTP传输文件
 * @author Administrator
 *
 */
public class RemoteSFTP {
	private Connection conn;
	@SuppressWarnings("unused")
	private String charset;

	public void downloadWithSSL(SSHClient client, String sourcePath, File targetFile) {
		FileOutputStream out = null;
		if (doLogin(client)) {
			try {
				out = new FileOutputStream(targetFile);
				SFTPv3Client sFTPv3Client = new SFTPv3Client(conn);
				SFTPv3FileHandle handle = sFTPv3Client.openFileRO(sourcePath);
				byte[] cache = new byte[1024];
				int i = 0;
				int offset = 0;
				while((i = sFTPv3Client.read(handle, offset, cache, 0, 1024)) != -1){
					out.write(cache, 0, i);
					offset += i;
				}
				sFTPv3Client.closeFile(handle);
		        if (handle.isClosed()){
				    sFTPv3Client.close();
		        } 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void uploadWithSSL(SSHClient client, File sourceFile, String targetPath) {
		FileInputStream in = null;
		if (doLogin(client)) {
			try {
				in = new FileInputStream(sourceFile);
				int length = in.available();
				SFTPv3Client sFTPv3Client = new SFTPv3Client(conn);
				SFTPv3FileHandle handle = sFTPv3Client.createFileTruncate(targetPath);
				byte[] cache = new byte[length];
				in.read(cache);
				sFTPv3Client.write(handle, 0, cache, 0, length);
				sFTPv3Client.closeFile(handle);
		        if (handle.isClosed()){
				    sFTPv3Client.close();
		        } 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

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
}
