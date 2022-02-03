package com.reservationalarm.theater;

import com.reservationalarm.movie.MovieBatchOuterTasklet;
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
public class TheaterBatchJob {
    private final TheaterBatchOuterTasklet theaterBatchOuterTasklet;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job theaterBatchTasklet(Step theaterBatchTaskletStep){
        return jobBuilderFactory.get("theaterBatchTaskletJob")
                .incrementer(new RunIdIncrementer())
                .flow(theaterBatchTaskletStep)
                .end()
                .build();
    }

    @Bean
    public Step theaterBatchTaskletStep(){
        return stepBuilderFactory
                .get("theaterBatchTaskletStep")
                .tasklet(theaterBatchOuterTasklet)
                .build();
    }
}
