package com.reservationalarm.theater;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TheaterBatchScheduler {
    private final Job theaterBatchTasklet;      //TheaterBatchJob
    private final JobLauncher jobLauncher;

    // 한달마다 실행
    @Scheduled(fixedDelay = 259200 * 1000L)
    public void executeJob(){
        try{
            jobLauncher.run(
                    theaterBatchTasklet,
                    new JobParametersBuilder()
                            .addString("datetime", LocalDateTime.now().toString())
                            .toJobParameters()
            );
        } catch(JobExecutionException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
