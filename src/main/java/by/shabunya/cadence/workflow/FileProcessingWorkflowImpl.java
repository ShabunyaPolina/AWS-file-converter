package by.shabunya.cadence.workflow;

import by.shabunya.cadence.activity.FileProcessingActivityWorker;
import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;

import java.time.Duration;

public class FileProcessingWorkflowImpl implements FileProcessingWorkflow {
    private final ActivityOptions options =
            new ActivityOptions.Builder()
                    .setTaskList(TASK_LIST)
                    .setScheduleToCloseTimeout(Duration.ofSeconds(10))
                    .build();
    private final FileProcessingActivityWorker fileProcessingActivityWorker =
            Workflow.newActivityStub(FileProcessingActivityWorker.class, options);

    @Override
    public void startFileProcessing(String fileName) {
        String newFileName = fileProcessingActivityWorker.createNewFileName(fileName);
        fileProcessingActivityWorker.downloadFileFromCloudBucket(fileName);
        newFileName = fileProcessingActivityWorker.convertCSVFileToXLS(fileName, newFileName);
        fileProcessingActivityWorker.uploadFileToCloudBucket(newFileName);
    }
}
