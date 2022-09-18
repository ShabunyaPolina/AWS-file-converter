package by.shabunya.cadence.workflow;

import by.shabunya.cadence.activity.FileProcessingActivityWorker;
import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;

import java.time.Duration;

public class FileProcessingWorkflowImpl implements FileProcessingWorkflow {
    private final FileProcessingActivityWorker fileProcessingActivityWorker;

    public FileProcessingWorkflowImpl() {
        ActivityOptions options =
                new ActivityOptions.Builder()
                        .setTaskList(TASK_LIST)
                        .setScheduleToCloseTimeout(Duration.ofSeconds(10))
                        .build();

        this.fileProcessingActivityWorker =
                Workflow.newActivityStub(FileProcessingActivityWorker.class, options);
    }

    @Override
    public void startFileProcessing(String fileName) {
        String newFileName = fileProcessingActivityWorker.createNewFileName(fileName);
        fileProcessingActivityWorker.downloadFileFromCloudBucket(fileName);
        fileProcessingActivityWorker.convertFileToXLS(fileName);
        fileProcessingActivityWorker.uploadFileToCloudBucket();
    }
}
