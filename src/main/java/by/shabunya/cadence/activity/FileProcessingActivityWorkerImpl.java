package by.shabunya.cadence.activity;
public class FileProcessingActivityWorkerImpl implements FileProcessingActivityWorker {

    @Override
    public void createNewFileName() {
        System.out.println(1);
    }

    @Override
    public void downloadFileFromCloudBucket() {
        System.out.println(2);
    }

    @Override
    public void convertFileToXLS() {
        System.out.println(3);
    }

    @Override
    public void uploadFileToCloudBucket() {
        System.out.println(4);
    }
}
