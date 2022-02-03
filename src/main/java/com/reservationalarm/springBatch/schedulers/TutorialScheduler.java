package com.reservationalarm.springBatch.schedulers;

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
public class TutorialScheduler {

    private final Job tutorialJob;  // tutorialJob
    private final JobLauncher jobLauncher;

    // 5초마다 실행
    @Scheduled(fixedDelay = 80000 * 1000L)
    public void executeJob () {
        try {
            jobLauncher.run(
                    tutorialJob,
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
