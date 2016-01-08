package org.cc.automate.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.cc.automate.config.domain.NodehostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class StringUtil {
	private static Logger LOG = LoggerFactory.getLogger(StringUtil.class);
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

	private static List<String> patterns = new ArrayList<String>();

	static {
		patterns.add("yyyy-MM-dd HH:mm:ss");
		patterns.add("yyyy-MM-dd");
		patterns.add("yyyy/MM/dd HH:mm:ss");
		patterns.add("yyyy/MM/dd");
	}

	// 转换到Date
	public static Date convert(String text) {
		if (text == null) {
			return null;
		}

		for (String pattern : patterns) {
			Date date = tryConvert(text, pattern);
			if (date != null) {
				return date;
			}
		}

		return null;
	}

	public static String parseDateTime(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String parseTimeStamp(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static Date tryConvert(String text, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(text);
		} catch (ParseException ex) {
			logger.debug(ex.getMessage(), ex);
		}

		return null;
	}

	public static String getUUID() {
		UUID id = UUID.randomUUID();
		return id.toString();
	}

	public static boolean hasLength(String source) {
		if (source == null || source.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isNullOrEmpty(String text) {
		boolean flag = true;
		if (text != null && !"".equals(text) && !"".equals(text.trim())) {
			flag = false;
		}

		return flag;
	}

	public static String getTimeString(Date date) {
		return timeFormat.format(date);
	}

	public static String getDateString(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 报表中数据源Sql使用当前用户替换
	 */
	public static String replaceCurrentUserId(String text, String value) {
		if (text.contains(":CURRENT_USER_ID")) {
			text = text.replaceAll("CURRENT_USER_ID", value);
			if (text.contains(":")) {
				String[] subtext = text.split(":");
				String newText = "";
				for (String t : subtext) {
					newText += t;
				}
				return newText;
			} else {
				return text;
			}
		} else {
			return text;
		}
	}

	/**
	 * 获取请求的IP地址
	 */
	public static String getIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 判断数据中是否有重复的元素
	 * 
	 * @return
	 */
	public static boolean hasDuplicate(String[] ss) {
		boolean flag = false;
		if (ss != null && ss.length > 0) {
			Set<String> set = new HashSet<String>();
			for (String s : ss) {
				if (!set.contains(s)) {
					set.add(s);
				}
			}

			if (ss.length > set.size()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 判断集合中是否有重复数据
	 * 
	 * @param ss
	 * @return
	 */
	public static boolean hasDuplicate(List<String> ss) {
		boolean flag = false;
		if (ss != null && ss.size() > 0) {
			Set<String> set = new HashSet<String>();
			for (String s : ss) {
				if (!set.contains(s)) {
					set.add(s);
				}
			}

			if (ss.size() > set.size()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 将REQUEST的键值对抓换为单值的
	 * 
	 * @param mapOld
	 * @return
	 */
	public static Map<String, Object> toSingleValueMap(Map<String, String[]> mapOld) {
		Map<String, Object> mapNew = new HashMap<String, Object>();
		for (String key : mapOld.keySet()) {
			if (mapOld.get(key) == null) {
				continue;
				// mapNew.put(key, null);
			} else {
				if (isNullOrEmpty(mapOld.get(key)[0])) {
					continue;
				} else {
					mapNew.put(key, mapOld.get(key)[0]);
				}
			}
		}
		return mapNew;
	}

	/**
	 * 去除换行
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/*-----------------------------------
	
	笨方法：String s = "你要去除的字符串";
	
	        1.去除空格：s = s.replace('\\s','');
	
	        2.去除回车：s = s.replace('\n','');
	
	这样也可以把空格和回车去掉，其他也可以照这样做。
	
	注：\n 回车(\u000a) 
	\t 水平制表符(\u0009) 
	\s 空格(\u0008) 
	\r 换行(\u000d)*/
	/**
	 * 判断字符串可转换为数字
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isDouble(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// JSON BLANK DETAIL

	/**
	 * 将字符串转换为JAVA DTO
	 * @param text
	 * @return
	 */
	public static List<NodehostDTO> JsonFromStr(String text) {
		LOG.info("parse text , {}", text);
		List<NodehostDTO> nodehostDTOs = new ArrayList<NodehostDTO>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ArrayNode arrayNode = (ArrayNode)mapper.readTree(text);
			for(Iterator<JsonNode> iterator = arrayNode.elements(); iterator.hasNext(); ){
				JsonNode jsonNode = iterator.next();
				for (Iterator<Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext();) {
					Entry<String, JsonNode> entry = it.next();
					mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					NodehostDTO nodehostDTO = mapper.treeToValue(entry.getValue(), NodehostDTO.class);
					nodehostDTOs.add(nodehostDTO);
				}
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return nodehostDTOs;
	}
	
	/**
	 * 去除JSON字符串KEY的空格
	 * @param jsonStr
	 * @return
	 */
	public static String JsonTrimKey(String jsonStr){
		String[] ss1 = jsonStr.split("\r\n");
		StringBuilder content = new StringBuilder();
		for(String s : ss1){
			if(s.contains(":")){
				String[] ss2 = s.split(":");
				for(int i=0, len=ss2.length; i<len; i++){
					if(i == 0){
						content.append(StringUtil.replaceBlank(ss2[i]));
					}else{
						content.append(":" + ss2[i]);
					}
				} 
				content.append("\r\n");
			}else{
				content.append(s + "\r\n");
			}
		}
		return content.toString();
	}
	
	/**
	 * 添加元素之间的,，否则无法进行解析
	 * @param jsonStr
	 * @return
	 */
	public static String JsonMarkElement(String jsonStr){
		jsonStr = "[" + jsonStr + "]";
		return jsonStr.replaceAll("\\}\r\n\\{", "\\},\r\n\\{");
	}
	
	public static boolean isWanaIpInterface(String text){
		Pattern pattern = Pattern.compile("(eth[0-9])|(br\\-ex*)|(br\\-eth[0-9])", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher.find();
	}
	
	public static void main(String[] args) {
		System.out.println(isWanaIpInterface("etha"));
	}

	/**
	 * @Title: JsonStrTrim
	 * @author : jsw
	 * @date : 2012-12-7
	 * @time : 上午09:19:18
	 * @Description: 传入string 类型的 json字符串 去处字符串中的属性值的空格
	 * @param jsonStr
	 * @return
	 * @exception:(异常说明)
	 
	public static JsonNode JsonStrTrim(String jsonStr) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(jsonStr);
			for (Iterator<Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext();) {
				Entry<String, JsonNode> entry = it.next();
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
				// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				// false);
				// NodehostDTO nodehostDTO =
				// mapper.treeToValue(entry.getValue(), NodehostDTO.class);
				System.out.println(1);
			}

			return jsonNode;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		 * JsonFactory f = new JsonFactory(); JsonParser parser = null; TreeNode
		 * treeNode = null;
		 * 
		 * parser = f.createParser(jsonStr);
		 * 
		 * treeNode = parser.readValueAsTree();
		 * 
		 * for (Iterator<String> it = treeNode.fieldNames(); it.hasNext();) {
		 * String key = it.next(); key = key + "changed";
		 * System.out.println(key); System.out.println(treeNode.get(key));
		 * System.out.println(1); }
		 * 
		 * return treeNode;

	}
**/
	//public static void main(String[] args) {
		/*try {
			JsonStrTrim(FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\json.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	//}

	/**
	 * @Title: JsonStrTrim
	 * @author : jsw
	 * @date : 2012-12-7
	 * @time : 上午09:21:48
	 * @Description: 传入jsonObject 去除当中的空格
	 * @param jsonStr
	 * @return
	 * @exception:(异常说明)
	 * 
	 * 						public
	 *                       JSONObject JsonStrTrim(JSONObject jsonStr){
	 * 
	 *                       ObjectMapper mapper = new ObjectMapper(); mapper.re
	 *                       JSONObject reagobj = jsonStr ; // 取出 jsonObject
	 *                       中的字段的值的空格 Iterator itt = reagobj.keys();
	 * 
	 *                       while (itt.hasNext()) {
	 * 
	 *                       String key = itt.next().toString(); String value =
	 *                       reagobj.getString(key);
	 * 
	 *                       if(value == null){ continue ; }else
	 *                       if("".equals(value.trim())){ continue ; }else{
	 *                       reagobj.put(key, value.trim()); } } return reagobj;
	 *                       }
	 */
	/**
	 * @Title: JsonStrTrim
	 * @author : jsw
	 * @date : 2012-12-7
	 * @time : 上午11:48:59
	 * @Description: 将 jsonarry 的jsonObject 中的value值去处前后空格
	 * @param arr
	 * @return
	 * @exception:(异常说明)
	 * 
	 * 						public
	 *                       JSONArray JsonStrTrim(JSONArray arr) {
	 * 
	 *                       if (arr != null && arr.size() > 0) { for (int i =
	 *                       0; i < arr.size(); i++) {
	 * 
	 *                       JSONObject obj = (JSONObject) arr.get(i); // 取出
	 *                       jsonObject 中的字段的值的空格 Iterator itt = obj.keys();
	 * 
	 *                       while (itt.hasNext()) {
	 * 
	 *                       String key = itt.next().toString(); String value =
	 *                       obj.getString(key);
	 * 
	 *                       if (value == null) { continue; } else if
	 *                       ("".equals(value.trim())) { continue; } else {
	 *                       obj.put(key, value.trim()); } } arr.set(i, obj); }
	 *                       } return arr; }
	 */
}
