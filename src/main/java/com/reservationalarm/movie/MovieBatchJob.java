package com.reservationalarm.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MovieBatchJob {
    private final OuterTasklet outerTasklet;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job movieBatchTasklet(Step movieBatchTaskletStep){
        return jobBuilderFactory.get("movieBatchTaskletJob")
                .incrementer(new RunIdIncrementer())
                .flow(movieBatchTaskletStep)
                .end()
                .build();
    }

    @Bean
    public Step movieBatchTaskletStep(){
        return stepBuilderFactory
                .get("movieBatchTaskletStep")
                .tasklet(outerTasklet)
                .build();
    }
}
