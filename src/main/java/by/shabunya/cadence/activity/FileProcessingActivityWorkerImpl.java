package by.shabunya.cadence.activity;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.filetypes.SpreadsheetFileType;
import com.groupdocs.conversion.options.convert.SpreadsheetConvertOptions;
import com.groupdocs.conversion.options.load.CsvLoadOptions;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileProcessingActivityWorkerImpl implements FileProcessingActivityWorker {

    private final String AWSBucketName;
    private final String userDirectory = System.getProperty("user.dir");
    private final AmazonS3 s3Client;

    public FileProcessingActivityWorkerImpl(String AWSBucketName, String accessKey, String secretKey) {
        this.AWSBucketName = AWSBucketName;

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Override
    public String createNewFileName(String fileName) {
        System.out.print("1. Create a new file name: ");

        int dotPos = fileName.lastIndexOf('.');

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        String newFileName = fileName.substring(0, dotPos)
                + currentDate
                + fileName.substring(dotPos);

        System.out.println(newFileName);

        return newFileName;
    }

    @Override
    public void downloadFileFromCloudBucket(String fileName) {
        System.out.println("2. Download a csv file");

        GetObjectRequest request = new GetObjectRequest(AWSBucketName, fileName);
        File newFile = new File(userDirectory + "\\" + fileName);

        s3Client.getObject(request, newFile);
    }

    @Override
    public String convertFileToXLS(String fileName, String newFileName) {
        System.out.println("3. Convert file xls format");

        CsvLoadOptions loadOptions = new CsvLoadOptions();
        loadOptions.setSeparator(',');
        Converter converter = new Converter(userDirectory + "\\" + fileName, loadOptions);

        SpreadsheetConvertOptions options = new SpreadsheetConvertOptions();
        options.setFormat(SpreadsheetFileType.Xls);

        String convertedFileName = newFileName.substring(0, newFileName.lastIndexOf(".")) + ".xls";

        converter.convert(userDirectory + "\\" + convertedFileName, options);

        return convertedFileName;
    }

    @Override
    public void uploadFileToCloudBucket(String fileName) {
        System.out.println("4. Upload a new file");

        File file = new File(userDirectory + "\\" + fileName);

        s3Client.putObject(AWSBucketName, fileName, file);
    }
}
