function showGraph(processInstanceId) {
	window.open('../bpm/bpm-process-instance-graph.do?processInstanceId=' + processInstanceId);
}

function showGraphDefinition(key){
	window.open('../bpm/bpm-process-definition-graph.do?processDefinitionKey=' + key);
}