package com.poc.batch.listeners;

import com.poc.batch.entities.StoreOrder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnSkipInWrite;

@Slf4j
public class JobExceptionSkipListener {

    private final Logger logger = LoggerFactory.getLogger(JobExceptionSkipListener.class);

    @OnReadError
    public void problemOnRead(Exception ex) {
        logger.error("Error on Reading CSV is:" + ex.getMessage());
    }

    @OnProcessError
    public void problemOnProcess(StoreOrder storeOrder, Exception ex) {
        logger.info("Error on Processing  is:" + ex.getMessage() + " Input is:" + storeOrder.toString());
    }

    @OnSkipInWrite
    public void problemOnWrite(StoreOrder storeOrder, Throwable ex) {
        logger.info("Error on Writing  is:" + ex.getMessage());
    }

}
