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
public class StepNextJobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextJob() {
        return this.jobBuilderFactory.get("stepNextJob01")
                .start(nextStep01())
                .next(nextStep02())
                .next(nextStep03())
                .build();
    }

    @Bean
    public Step nextStep01() {
        return this.stepBuilderFactory.get("nextStep01")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>> this is nextStep01");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step nextStep02() {
        return this.stepBuilderFactory.get("nextStep02")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>> this is nextStep02");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step nextStep03() {
        return this.stepBuilderFactory.get("nextStep03")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>> this is nextStep03");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
