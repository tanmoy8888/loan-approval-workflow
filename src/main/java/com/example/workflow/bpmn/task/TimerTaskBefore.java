package com.example.workflow.bpmn.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.workflow.util.DateUtil;

@Component(value = "TimerTaskBefore")
public class TimerTaskBefore /* implements JavaDelegate */ {

	@Autowired
	RuntimeService runtimeservice;

	/*
	 * @Override public void execute(DelegateExecution execution) throws Exception {
	 * 
	 * System.out.println("Returning the log message :::: ");
	 * 
	 * this.runtimeservice.createVariableInstanceQuery().list().forEach(System.out::
	 * println); }
	 */

	/*
	 * public Date duration(boolean status) { if (status) {
	 * System.out.println("Inside  TimerTaskBefore Bean true");
	 * //this.runtimeservice.createVariableInstanceQuery().list().forEach(System.out
	 * ::println); this.getAllVariables();
	 * //System.out.println("All Variables :: "+this.getAllVariables());
	 * 
	 * System.out.println("Activation date from TimerTaskBefore :::::: "+this.
	 * getValue("activation_date"));
	 * 
	 * System.out.println("Gettig Variables");
	 * 
	 * String activation=this.runtimeservice.createVariableInstanceQuery()
	 * .list().stream() .filter(a ->
	 * a.getName().equals("activation_date")).findFirst().get().toString();
	 * 
	 * System.out.println("activation-------------------->"+activation);
	 * 
	 * System.out.println("All Variables :: "+this.getAllVariables());
	 * 
	 * 
	 * return DateUtil.convertDateFromString(this.getValue("activation_date"));
	 * //this.runtimeservice.createVariableInstanceQuery().list(). } else {
	 * System.out.println("Inside  TimerTaskBefore Bean false"); return
	 * DateUtil.convertDateFromString(this.getValue("activation_date")); }
	 * 
	 * //return "PT10S";
	 * 
	 * }
	 */

	//// original method...
	
   /*
	public Date duration(boolean status) {
		Date val=null;
		if(status) {
		System.out.println("All Variables :: "+this.getAllVariables());
		val=DateUtil.convertDateFromString(this.getValue("activation_date"));
		System.out.println("val----------->"+val);
		}
		else {
			val=DateUtil.convertDateFromString(this.getValue("activation_date"));
			System.out.println("val----------->"+val);
		}
		
		return val;
	}
	*/
	
	public Date duration(String processInstanceid , boolean status) {
		System.out.println("Inside TimerTaskBefore");
		Date val=null;
		if(status) {
		System.out.println("All Variables :: "+this.getAllVariables());
		val=DateUtil.convertDateFromString(this.getValue("activation_date"));
		System.out.println("val------if----->"+val);
		}
		else {
			val=DateUtil.convertDateFromString(this.getValue("activation_date"));
			System.out.println("val----else------->"+val);
		}
		
		return val;
	}
	public String getValue(String key) {
		String val = "unavailable";
		Optional<VariableInstance> instance = this.runtimeservice.createVariableInstanceQuery().list().stream()
				.filter(a -> a.getName().equals(key)).findFirst();
		if (instance.isPresent()) {
			System.out.println("Inside getVlaue() if().........");
			if (instance.get().getValue() != null && !instance.get().getValue().equals("")) {
				System.out.println("Inside part 2 if");
				val = instance.get().getValue().toString();
			}
			else {
				System.out.println("Inside part 2 else");
			}
		}
		else {
			System.out.println("Inside getVlaue() else()...........");
			val="";
		}
		// return instance.isPresent() ? instance.get().getValue().toString() :
		// "unavailable";
		return val;
	}
	// .findFirst().get().getValue().toString();

	public Map<String, Object> getAllVariables() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.runtimeservice.createVariableInstanceQuery().list().stream()
				.forEach(a -> System.out.println("Variable Name :: " + " a.getId() ->" + a.getId() + " a"
						+ a.getTenantId() + "  a.getTypeName()" + a.getTypeName() + " a.getTypedValue()"
						+ a.getTypedValue() + map.put(a.getName(), a.getValue())));

		return map;

	}

}
