package by.shabunya.cadence.activity;

import com.uber.cadence.activity.ActivityMethod;

public interface FileProcessingActivityWorker {
    @ActivityMethod
    void createNewFileName();
    @ActivityMethod
    void downloadFileFromCloudBucket();
    @ActivityMethod
    void convertFileToXLS();
    @ActivityMethod
    void uploadFileToCloudBucket();
}
