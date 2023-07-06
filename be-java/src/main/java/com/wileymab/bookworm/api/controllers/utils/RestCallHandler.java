package com.wileymab.bookworm.api.controllers.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class RestCallHandler<T extends Object> {

    private static final Logger LOG = LoggerFactory.getLogger(RestCallHandler.class);

    protected T result = null;
    protected Exception exception = null;

    public T getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public ResponseEntity<?> execute(CallRunner<T> callRunner) {
        ResponseEntity<?> response;
        try {
            this.result = callRunner.callToService();
        }
        catch(Exception e) {
            this.exception = e;
            LOG.error(e.getMessage(), e);
        }

        if (exception != null) {
            response = ResponseEntity.internalServerError().build();
        }
        else if (result != null) {
            response = ResponseEntity.ok(result);
        }
        else {
            response = ResponseEntity.notFound().build();
        }

        LOG.debug(String.format("Response Status Code: %s", response.getStatusCode()));
        LOG.debug(String.format("Response Result: %s", getResult()));
        LOG.debug(String.format("Response Exception: %s", getException()));
        return response;
    }

}

