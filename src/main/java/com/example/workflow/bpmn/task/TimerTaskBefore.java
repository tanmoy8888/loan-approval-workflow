package com.example.workflow.bpmn.task;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "TimerTaskBefore")
public class TimerTaskBefore implements JavaDelegate {

	@Autowired
	RuntimeService runtimeservice;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("Returning the log message :::: ");

		this.runtimeservice.createVariableInstanceQuery().list().forEach(System.out::println);
	}

	public String duration(boolean status) {
		if (status) {
			System.out.println("Inside  TimerTaskBefore Bean true");
			this.runtimeservice.createVariableInstanceQuery().list().forEach(System.out::println);
			System.out.println("Gettig Variables");
			
			
			
			//this.runtimeservice.createVariableInstanceQuery().list().
		} else {
			System.out.println("Inside  TimerTaskBefore Bean false");
		}

		return "PT10S";
	}

}
