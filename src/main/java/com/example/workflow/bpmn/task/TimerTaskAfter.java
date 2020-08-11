package com.example.workflow.bpmn.task;

import java.util.Optional;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "TimerTaskAfter")
public class TimerTaskAfter  implements JavaDelegate
{
	
	@Autowired
	RuntimeService runtimeservice;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("Returning the log message :::: ");
		
		this.runtimeservice.createVariableInstanceQuery().list().forEach(System.out::println);
	}
	
	public String duration(boolean status) {
		if(status) {
			System.out.println("Inside  TimerTaskAfter Bean true");
		}
		else {
			System.out.println("Inside  TimerTaskAfter Bean false");
		System.out.println("From TimerTask Class ::: ");
		this.runtimeservice.createVariableInstanceQuery().list().forEach(System.out::println);
		
		/*
							 * this.runtimeservice.createVariableInstanceQuery() .list().stream() .filter(a
							 * ->
							 * a.getName().equals("activation_date")).findFirst().get().getValue().toString(
							 * );
							 * 
							 * 
							 */
		
		String activation = this.getValue("activation_date");
		System.out.println("activation-------------------->"+activation);
		
		}
		return "PT10S";
	}
	
	public String getValue(String key) {
		Optional<VariableInstance> instance= this.runtimeservice.createVariableInstanceQuery()
		.list().stream()
		.filter(a -> a.getName().equals("activation_date"))
		.findFirst();
		return instance.isPresent() ? instance.get().getValue().toString() : "unavailable";
		//.findFirst().get().getValue().toString();
		
	}


}
