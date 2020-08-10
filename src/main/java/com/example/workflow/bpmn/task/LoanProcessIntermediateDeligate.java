package com.example.workflow.bpmn.task;

import java.util.Date;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Job;

public class LoanProcessIntermediateDeligate implements JavaDelegate {
	  private long starttime = System.currentTimeMillis();

	  private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	  public void execute(DelegateExecution execution) throws Exception {
	    
	    LOGGER.info("Getting variables :: "+execution.getVariables());
	  }
	  
		/*
		 * protected void setTimer(String processInstanceId, String timerName, Date
		 * newValue) { Job timerJob =
		 * getEngine().getManagementService().createJobQuery().processInstanceId(
		 * processInstanceId) .activityId(timerName).active().singleResult();
		 * getEngine().getManagementService().setJobDuedate(timerJob.getId(), newValue);
		 * }
		 */
	  
	  
}
