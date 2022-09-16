package by.shabunya.cadence.response;

public class WorkflowResponse {
    private String workflowId;
    private int statusCode;
    private String message;

    public WorkflowResponse(String workflowId, int statusCode, String message) {
        this.workflowId = workflowId;
        this.statusCode = statusCode;
        this.message = message;
    }

    public WorkflowResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}