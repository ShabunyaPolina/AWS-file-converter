package by.shabunya.cadence.activity;

import com.uber.cadence.activity.ActivityMethod;

public interface FileProcessingActivityWorker {

    String createNewFileName(String fileName);

    void downloadFileFromCloudBucket(String fileName);

    String convertFileToXLS(String fileName, String newFileName);

    void uploadFileToCloudBucket(String fileName);
}
