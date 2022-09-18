package by.shabunya.cadence.controller;

import by.shabunya.cadence.response.WorkflowResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<WorkflowResponse> handleMissingParams() {
        return new ResponseEntity<>(new WorkflowResponse(HttpStatus.NOT_FOUND.value(),
                "Incorrect request"),
                HttpStatus.NOT_FOUND);
    }
}
