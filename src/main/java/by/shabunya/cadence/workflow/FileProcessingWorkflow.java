package by.shabunya.cadence.workflow;

import com.uber.cadence.workflow.WorkflowMethod;

public interface FileProcessingWorkflow {
    String TASK_LIST = "File processing";

    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 300, taskList = TASK_LIST)
    void startFileProcessing(String fileName);
}
