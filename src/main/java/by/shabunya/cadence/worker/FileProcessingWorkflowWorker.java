package by.shabunya.cadence.worker;

import by.shabunya.cadence.activity.FileProcessingActivityWorker;
import by.shabunya.cadence.workflow.FileProcessingWorkflow;
import by.shabunya.cadence.workflow.FileProcessingWorkflowImpl;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FileProcessingWorkflowWorker {
    private final WorkerFactory workerFactory;
    private final FileProcessingActivityWorker activityImpl;

    public FileProcessingWorkflowWorker(WorkerFactory workerFactory, FileProcessingActivityWorker activityImpl) {
        this.workerFactory = workerFactory;
        this.activityImpl = activityImpl;
    }

    @PostConstruct
    public void startWorkerFactory() {
        Worker worker = workerFactory.newWorker(FileProcessingWorkflow.TASK_LIST);
        worker.registerWorkflowImplementationTypes(FileProcessingWorkflowImpl.class);

        worker.registerActivitiesImplementations(activityImpl);
        workerFactory.start();
    }
}
