/*
 * package com.example.workflow;
 * 
 * import static org.junit.Assert.assertThat; import static
 * org.mockito.Mockito.times; import static org.mockito.Mockito.verify; import
 * static org.mockito.Mockito.when;
 * 
 * import java.util.Arrays; import java.util.Date; import java.util.HashMap;
 * import java.util.List; import java.util.Map;
 * 
 * import org.camunda.bpm.engine.ProcessEngine; import
 * org.camunda.bpm.engine.RuntimeService; import
 * org.camunda.bpm.engine.test.Deployment; import
 * org.camunda.bpm.engine.test.mock.Mocks; import
 * org.camunda.bpm.scenario.ProcessScenario; import
 * org.camunda.bpm.scenario.Scenario; import
 * org.camunda.bpm.scenario.act.TimerIntermediateEventAction; import
 * org.camunda.bpm.scenario.act.UserTaskAction; import
 * org.camunda.bpm.scenario.defer.Deferred; import
 * org.camunda.bpm.scenario.delegate.ProcessInstanceDelegate; import
 * org.camunda.bpm.scenario.delegate.TaskDelegate; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.Mock; import
 * org.mockito.Mockito; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.example.workflow.bpmn.task.TimerTaskBefore; import static
 * org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
 * 
 * 
 * @SpringBootTest
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @Deployment(resources = "loan-approval-workflow-junit.bpmn") public class
 * NewWorkFlowTest_Final_Working {
 * 
 * 
 * @Autowired public RuntimeService runtimeService;
 * 
 * 
 * @Autowired ProcessEngine processEngine;
 * 
 * 
 * 
 * @Mock private ProcessScenario scenario;
 * 
 * private List<String> actionList = Arrays.asList("user_check_criteria" ,
 * "issue_loan_system" ,"Timer_1"
 * ,"Check_Further_system","Timer_2","user_final_approval");
 * 
 * 
 * 
 * @Test public void startTest() { System.out.
 * println("Inside Start test..............................................................."
 * );
 * 
 * Map<String,Object> map = new HashMap<>(); map.put("activation_date", (new
 * Date(System.currentTimeMillis() +2000).toString()));
 * 
 * TimerTaskBefore timer= Mockito.mock(TimerTaskBefore.class);
 * 
 * when(timer.duration(Mockito.anyString() ,
 * Mockito.anyBoolean())).thenReturn((new Date(2000)));
 * Mocks.register("TimerTaskBefore", timer);
 * 
 * 
 * 
 * when(scenario.waitsAtUserTask("user_check_criteria")).thenReturn(new
 * UserTaskAction() {
 * 
 * @Override public void execute(TaskDelegate task) { System.out.
 * println("Inside user_check_criteria..........................................................."
 * ); task.complete(); } });
 * 
 * when(scenario.waitsAtServiceTask("issue_loan_system")).thenReturn((task)->{
 * System.out.
 * println("Inside issue_loan_system....................................................................."
 * ); task.complete(); });
 * 
 * 
 * 
 * 
 * when(scenario.waitsAtTimerIntermediateEvent("Timer_1")).thenReturn(new
 * TimerIntermediateEventAction() {
 * 
 * @Override public void execute(ProcessInstanceDelegate timer) { System.out.
 * println("Inside Timer_1............................................................"
 * ); timer.defer("PT4M30S", new Deferred() {
 * 
 * @Override public void execute() throws Exception { throw new Exception(); //
 * not expected } }); } });
 * 
 * when(scenario.waitsAtServiceTask("Check_Further_system")).thenReturn((task)->
 * { System.out.
 * println("Inside Check_Further_system......................................................................"
 * ); task.complete(); });
 * 
 * 
 * when(scenario.waitsAtTimerIntermediateEvent("Timer_2")).thenReturn(new
 * TimerIntermediateEventAction() {
 * 
 * @Override public void execute(ProcessInstanceDelegate timer) { System.out.
 * println("Inside Timer_2................................................................"
 * ); timer.defer("PT4M30S", new Deferred() {
 * 
 * @Override public void execute() throws Exception { throw new Exception(); //
 * not expected } }); } });
 * 
 * 
 * 
 * when(scenario.waitsAtUserTask("user_final_approval")).thenReturn(new
 * UserTaskAction() {
 * 
 * @Override public void execute(TaskDelegate task) { System.out.
 * println("Inside user_final_approval............................................................."
 * ); task.complete(); } });
 * 
 * 
 * 
 * System.out.
 * println("Started Scenario.........................................................................."
 * );
 * 
 * Scenario executed =Scenario.run(scenario).startByKey(
 * "my-camunda-workflow-timer-expression-junit").execute(); System.out.
 * println("After Scenario.........................................................................."
 * );
 * 
 * verify(scenario, times(1)).hasFinished("user_check_criteria");
 * verify(scenario, times(1)).hasFinished("issue_loan_system"); verify(scenario,
 * times(1)).hasFinished("Timer_1"); verify(scenario,
 * times(1)).hasFinished("Check_Further_system"); verify(scenario,
 * times(1)).hasFinished("Timer_2"); verify(scenario,
 * times(1)).hasFinished("user_final_approval");
 * 
 * System.out.
 * println("After verify.........................................................................."
 * );
 * 
 * assertThat(executed.instance(scenario)).hasPassedInOrder(
 * "user_check_criteria", "issue_loan_system",
 * "Timer_1","Check_Further_system","Timer_2","user_final_approval");
 * 
 * System.out.println("After Assert..........................................");
 * } }
 */