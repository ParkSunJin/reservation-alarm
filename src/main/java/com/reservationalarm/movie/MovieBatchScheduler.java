package com.reservationalarm.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MovieBatchScheduler {
    private final Job movieBatchTasklet;  // MovieBatchJob
    private final JobLauncher jobLauncher;

    // 하루마다 실행
    @Scheduled(fixedDelay = 86400 * 1000L)
    public void executeJob () {
        try {
            jobLauncher.run(
                    movieBatchTasklet,
                    new JobParametersBuilder()
                            .addString("datetime", LocalDateTime.now().toString())
                            .toJobParameters()  // job parameter 설정
            );
        } catch (JobExecutionException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
