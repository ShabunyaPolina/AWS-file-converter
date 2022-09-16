package by.shabunya.cadence.controller;

import by.shabunya.cadence.response.WorkflowResponse;
import by.shabunya.cadence.workflow.FileProcessingWorkflow;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file-processing-workflow")
public class FileProcessingWorkflowLauncher {
    private final WorkflowClient workflowClient;

    public FileProcessingWorkflowLauncher(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @GetMapping
    public ResponseEntity<WorkflowResponse> startWorkflow(@RequestParam String fileName) {

        // TODO (no file name or bad format)
//            HttpStatus status = HttpStatus.BAD_REQUEST;
//            return new ResponseEntity<>(
//                    new WorkflowResponse(status.value(),
//                            "No file name"), status);

        FileProcessingWorkflow workflow = workflowClient.newWorkflowStub(FileProcessingWorkflow.class);
        WorkflowExecution execution = WorkflowClient.start(workflow::startFileProcessing, fileName);

        return new ResponseEntity<>(new WorkflowResponse(execution.getWorkflowId(),
                HttpStatus.OK.value(), "Workflow started successfully."), HttpStatus.OK);
    }
}
