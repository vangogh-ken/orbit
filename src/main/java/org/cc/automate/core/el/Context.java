package org.cc.automate.core.el;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author pzg
 *
 */
public class Context {
	private Map<String, Object> variables = new HashMap<String, Object>();
	public Context(){
		
	}
	
	public void setVariable(String variableName, Object variableValue){
		if(this.variables == null){
			this.variables = new HashMap<String, Object>();
		}
		this.variables.put(variableName, variableValue);
	}
	
	public Object getVariable(String variableName){
		return this.variables.get(variableName);
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		for(Entry<String, Object> entry : variables.entrySet()){
			this.setVariable(entry.getKey(), entry.getValue());
		}
	}
	
}
