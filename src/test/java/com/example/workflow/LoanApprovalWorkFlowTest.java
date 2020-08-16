package com.example.workflow;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.act.TimerIntermediateEventAction;
import org.camunda.bpm.scenario.act.UserTaskAction;
import org.camunda.bpm.scenario.defer.Deferred;
import org.camunda.bpm.scenario.delegate.ProcessInstanceDelegate;
import org.camunda.bpm.scenario.delegate.TaskDelegate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Deployment(resources = "loan-approval-workflow-junit.bpmn")
public class LoanApprovalWorkFlowTest {
	
	
	 @Autowired
	  public RuntimeService runtimeService;

	  
	  @Autowired
	   ProcessEngine  processEngine;

	  
	  
	  @Mock 
	  private ProcessScenario scenario;
	  
	  private List<String> actionList = Arrays.asList("user_check_criteria" , "issue_loan_system" ,"Timer_1" ,"Check_Further_system","Timer_2","user_final_approval");
	  
	  


	@Test
	public void startTest() {
	    Scenario.run(scenario).startByKey("my-camunda-workflow-timer-expression-junit").execute();

	    verify(scenario, times(1)).hasFinished("user_check_criteria");
	    verify(scenario, times(1)).hasFinished("issue_loan_system");
	    verify(scenario, times(1)).hasFinished("Timer_1");
	    verify(scenario, times(1)).hasFinished("Check_Further_system");
	    verify(scenario, times(1)).hasFinished("Timer_2");
	    verify(scenario, times(1)).hasFinished("user_final_approval");
	    
	    System.out.println("After verify..........................................................................");
	}
	
	@Test
	public void sequenceTest() {
		Scenario executed =Scenario.run(scenario).startByKey("my-camunda-workflow-timer-expression-junit").execute();
	    assertThat(executed.instance(scenario)).hasPassedInOrder("user_check_criteria", "issue_loan_system", "Timer_1","Check_Further_system","Timer_2","user_final_approval");
	    
	    System.out.println("After Assert..........................................");
	}
	
	@Before
	public void beforeTest() {
		
		actionList.forEach(				
				(action) ->{
					if(action.contains("user")) {
						  when(scenario.waitsAtUserTask(action)).thenReturn(new UserTaskAction() {
						      @Override
						      public void execute(TaskDelegate task) {
						    	  System.out.println("Inside user action...........................................................");
						        task.complete();
						      }
						    });
					}
						  else if(action.contains("system")) {
							  when(scenario.waitsAtServiceTask(action)).thenReturn((task)->{
							    	System.out.println("Inside system action......................................................................");
									  task.complete();
								  });
						  }
					
						  else if(action.contains("Timer")) {
							  when(scenario.waitsAtTimerIntermediateEvent(action)).thenReturn(new TimerIntermediateEventAction() {
							      @Override
							      public void execute(ProcessInstanceDelegate timer) {
							    	  System.out.println("Inside Timer action................................................................");
							        timer.defer("PT4M30S", new Deferred() {
							          @Override
							          public void execute() throws Exception {
							            throw new Exception(); // not expected
							          }
							        });
							      }
							    });
						  }
					});
	}
				
		
	}
	

