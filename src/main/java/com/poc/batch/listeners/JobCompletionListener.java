package com.poc.batch.listeners;

import com.poc.batch.entities.StoreOrder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class JobCompletionListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    public JobCompletionListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Executing job id " + jobExecution.getId());
    }

    private final Logger logger = LoggerFactory.getLogger(JobCompletionListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("============ JOB FINISHED ============ Verifying the results....\n");

            List<StoreOrder> results = jdbcTemplate.query("SELECT * FROM PUBLIC.STORE_ORDER",
                    (rs, row) -> StoreOrder.builder()
                            .id(rs.getLong(1))
                            .orderId(rs.getString(7))
                            .customerId(rs.getString(3))
                            .build());

            for (StoreOrder item : results) {
                logger.info("Discovered Order Id<" + item.orderId + "> in the database.");
            }

        }
    }
}
