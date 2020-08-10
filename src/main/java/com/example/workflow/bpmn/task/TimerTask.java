package com.example.workflow.bpmn.task;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "TimerTask")
public class TimerTask  implements JavaDelegate
{
	
	@Autowired
	RuntimeService runtimeservice;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("Returning the log message :::: ");
		
		this.runtimeservice.createVariableInstanceQuery().list().forEach(System.out::println);
	}
	
	public String duration() {
		System.out.println("From TimerTask Class ::: ");
		this.runtimeservice.createVariableInstanceQuery().list().forEach(System.out::println);
		return "PT10S";
	}


}
