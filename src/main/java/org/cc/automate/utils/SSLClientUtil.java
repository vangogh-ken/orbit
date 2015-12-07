package org.cc.automate.utils;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.cc.automate.core.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: <类功能描述>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年9月1日 下午4:59:22
 * @author Administrator
 * @version V1.0
 */
public class SSLClientUtil {

	private static final Logger LOG = LoggerFactory.getLogger(SSLClientUtil.class);
	
	public static void main(String[] args) {
		try {
			HttpClient client = getSSLHttpClient(getDefaultSSLConnectionSoecketFactory());
			long millisecond = new Date().getTime();
			String ip = "172.16.70.217";
			String urlLogin = String.format(Constant.IPMILOGIN, ip);
			String urlKvm = String.format(Constant.IPMIKVM, ip, ip, millisecond);
			String urlMedia = String.format(Constant.IPMIMEDIA, ip, ip, millisecond);
			HttpResponse response = client.execute(buildLoginPost(urlLogin, "admin", "huacloud"));
			
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200){
				String cookie = response.getFirstHeader("Set-Cookie").getValue();
				
				response = client.execute(buildDataGet(urlKvm, cookie));
				FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File("C:\\java jars\\ddd.jar"));
				
				response = client.execute(buildDataGet(urlMedia, cookie));
				FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File("C:\\java jars\\ddd2.jar"));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接工厂
	 * @return
	 */
	public static SSLConnectionSocketFactory getDefaultSSLConnectionSoecketFactory(){
		SSLConnectionSocketFactory socketFactory = null;
		try {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[]{manager}, null);
			socketFactory = new SSLConnectionSocketFactory(context, new NoopHostnameVerifier());//NoopHostnameVerifier 无主机校验的认证器
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage());
		} catch (KeyManagementException e) {
			LOG.error(e.getMessage());
		}
		
		return socketFactory;
	}
	
	/**
	 * 获取HttpClient
	 * @param sSLConnectionSocketFactory
	 * @return
	 */
	public static HttpClient getSSLHttpClient(SSLConnectionSocketFactory sSLConnectionSocketFactory){
		//配置
		RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD)
				.setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
		//注册
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sSLConnectionSocketFactory).build();
		//连接
		PoolingHttpClientConnectionManager connectiongManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		CloseableHttpClient httpClient = null;
		httpClient = HttpClients.custom().setConnectionManager(connectiongManager).setDefaultRequestConfig(defaultRequestConfig).build();
		return httpClient;
	}
	
	public static HttpPost buildLoginPost(String url, String username, String password){
		HttpPost post = new HttpPost(url);
		List<NameValuePair> values = new ArrayList<NameValuePair>();  
		values.add(new BasicNameValuePair("user", username));  
		values.add(new BasicNameValuePair("password", password));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values, Consts.UTF_8);
		post.setEntity(entity);
		
		return post;
	}
	
	public static HttpGet buildDataGet(String url, String cookie){
		HttpGet get = new HttpGet(url);
		get.setHeader("cookie", cookie);
		return get;
	}
	
	private static TrustManager manager = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] chain, String authType)  
                throws CertificateException {  
        }  

        public void checkServerTrusted(X509Certificate[] chain, String authType)  
                throws CertificateException {  
        }  

        public X509Certificate[] getAcceptedIssuers() {  
            return null;  
        }  
	};

}
