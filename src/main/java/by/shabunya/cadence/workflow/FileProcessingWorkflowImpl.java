package by.shabunya.cadence.workflow;

import by.shabunya.cadence.activity.FileProcessingActivityWorker;
import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;

import java.time.Duration;

public class FileProcessingWorkflowImpl implements FileProcessingWorkflow {
    @Override
    public void startFileProcessing(String fileName) {
        ActivityOptions options =
                new ActivityOptions.Builder()
                        .setTaskList(FileProcessingWorkflowImpl.TASK_LIST)
                        .setScheduleToCloseTimeout(Duration.ofSeconds(10))
                        .build();

        FileProcessingActivityWorker fileProcessingActivityWorker  =
                Workflow.newActivityStub(FileProcessingActivityWorker.class, options);

        fileProcessingActivityWorker.createNewFileName();
        fileProcessingActivityWorker.downloadFileFromCloudBucket();
        fileProcessingActivityWorker.convertFileToXLS();
        fileProcessingActivityWorker.uploadFileToCloudBucket();
    }
}
