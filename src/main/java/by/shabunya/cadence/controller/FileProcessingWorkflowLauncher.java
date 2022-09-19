package by.shabunya.cadence.controller;

import by.shabunya.cadence.response.WorkflowResponse;
import by.shabunya.cadence.workflow.FileProcessingWorkflow;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileProcessingWorkflowLauncher {
    private final WorkflowClient workflowClient;

    public FileProcessingWorkflowLauncher(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<WorkflowResponse> startWorkflow(@PathVariable String fileName) {
        if(!fileName.matches("[^:*\"<>]+\\.csv"))
            return new ResponseEntity<>(new WorkflowResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "File name " + fileName + " isn't correct or doesn't contain .csv extension"),
                    HttpStatus.BAD_REQUEST);

        FileProcessingWorkflow workflow = workflowClient.newWorkflowStub(FileProcessingWorkflow.class);
        WorkflowExecution execution = WorkflowClient.start(workflow::startFileProcessing, fileName);

        return new ResponseEntity<>(new WorkflowResponse(execution.getWorkflowId(),
                HttpStatus.OK.value(), "Workflow started successfully for file: " + fileName),
                HttpStatus.OK);
    }
}
