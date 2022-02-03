package com.reservationalarm.springBatch.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class TutorialTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        //log.debug("executed tasklet !!");
        System.out.println("executed tasklet !!");
        return RepeatStatus.FINISHED;
    }
}
