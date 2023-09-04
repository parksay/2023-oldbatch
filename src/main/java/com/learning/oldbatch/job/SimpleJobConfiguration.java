package com.learning.oldbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public  StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        return this.jobBuilderFactory.get("simpleJob01")
                .start(simpleStep01())
                .build();
    }

    @Bean
    public Step simpleStep01() {
        return this.stepBuilderFactory.get("simpleStep01")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>> this is simpleStep01");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
