package org.cc.automate.z.test;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONTest {
	public StringBuilder content = new StringBuilder();
	
	@Test
	public void t1(){
		content.append("{\"id\":\"idddd\", \"name\": \"test\"}");
	}
	
	@Test
	public void t(){
		content.append("{\"id\":\"idddd\", \"name\": \"test\"}");
		System.out.println(content.toString());
		ObjectMapper mapper = new ObjectMapper();
		try {
			TestTeam testTeam = mapper.readValue(content.toString(), TestTeam.class);
			System.out.println(testTeam.getId());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
