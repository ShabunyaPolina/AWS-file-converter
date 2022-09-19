package by.shabunya.cadence.activity;

public interface FileProcessingActivityWorker {

    String createNewFileName(String fileName);
    void downloadFileFromCloudBucket(String fileName);
    String convertCSVFileToXLS(String fileName, String newFileName);
    void uploadFileToCloudBucket(String fileName);
}
