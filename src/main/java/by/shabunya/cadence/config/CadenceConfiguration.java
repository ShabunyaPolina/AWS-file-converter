package by.shabunya.cadence.config;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadenceConfiguration {
    static final String DOMAIN = "Domain";

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
}
