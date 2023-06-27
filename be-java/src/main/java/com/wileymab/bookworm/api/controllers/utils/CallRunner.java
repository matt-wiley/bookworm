package com.wileymab.bookworm.api.controllers.utils;

public interface CallRunner<T> {
    T callToService() throws Exception;
}
