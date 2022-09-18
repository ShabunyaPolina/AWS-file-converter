package by.shabunya.cadence.activity;

import com.uber.cadence.activity.ActivityMethod;

public interface FileProcessingActivityWorker {
    @ActivityMethod
    String createNewFileName(String fileName);
    @ActivityMethod
    void downloadFileFromCloudBucket(String fileName);
    @ActivityMethod
    void convertFileToXLS();
    @ActivityMethod
    void uploadFileToCloudBucket();
}
