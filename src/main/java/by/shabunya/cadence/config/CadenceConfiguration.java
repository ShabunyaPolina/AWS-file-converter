package by.shabunya.cadence.config;

import by.shabunya.cadence.activity.FileProcessingActivityWorker;
import by.shabunya.cadence.activity.FileProcessingActivityWorkerImpl;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:AWSAccount.properties")
public class CadenceConfiguration {
    static final String DOMAIN = "Domain";

    @Value("${BUCKET_NAME}")
    private String AWSBucketName;
    @Value("${ACCESS_KEY}")
    private String AWSAccessKey;
    @Value("${SECRET_KEY}")
    private String AWSSecretKey;

    @Bean
    public WorkflowClient workflowClient(
            WorkflowServiceTChannel workflowServiceTChannel,
            WorkflowClientOptions workflowClientOptions) {
        return WorkflowClient.newInstance(workflowServiceTChannel, workflowClientOptions);
    }

    @Bean
    public WorkflowClientOptions workflowClientOptions() {
        return WorkflowClientOptions.newBuilder().setDomain(DOMAIN).build();
    }

    @Bean
    public ClientOptions clientOptions() {
        return ClientOptions.defaultInstance();
    }

    @Bean
    public WorkflowServiceTChannel workflowServiceTChannel(ClientOptions options) {
        return new WorkflowServiceTChannel(options);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public FileProcessingActivityWorker fileProcessingActivityWorker() {
        return new FileProcessingActivityWorkerImpl(AWSBucketName, AWSAccessKey, AWSSecretKey);
    }
}
