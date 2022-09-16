package by.shabunya.cadence.worker;

import by.shabunya.cadence.activity.FileProcessingActivityWorker;
import by.shabunya.cadence.activity.FileProcessingActivityWorkerImpl;
import by.shabunya.cadence.workflow.FileProcessingWorkflow;
import by.shabunya.cadence.workflow.FileProcessingWorkflowImpl;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FileProcessingWorkflowWorker {
    private final WorkerFactory workerFactory;

    public FileProcessingWorkflowWorker(WorkerFactory workerFactory) {
        this.workerFactory = workerFactory;
    }

    @PostConstruct
    public void startWorkerFactory() {
        createWorker();
        workerFactory.start();
    }

    private void createWorker() {
        Worker worker = workerFactory.newWorker(FileProcessingWorkflow.TASK_LIST);
        worker.registerWorkflowImplementationTypes(FileProcessingWorkflowImpl.class);

        FileProcessingActivityWorker activityImpl = new FileProcessingActivityWorkerImpl();
        worker.registerActivitiesImplementations(activityImpl);
    }
}
