package com.example.workflow.bpmn.task;

import java.util.logging.Logger;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service("loanProcessDeligate")
public class LoanProcessDeligate implements JavaDelegate {

	  private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");
	  
	  @Autowired
	  private RuntimeService runtimeService;

	  public void execute(DelegateExecution execution) throws Exception {
		  
         // These below variabes are put in the camunda engine		  
		  execution.setVariable("duration_time", "PT20S");		
		  execution.setVariable("activation_date", "2020-08-11T18:59:00");  
		  execution.setVariable("wish_date", "15-08-2020");
		  
		  
	    LOGGER.info("###################  ################################  Inside LoanProcessDeligate Processing request by ");
	  }
}
