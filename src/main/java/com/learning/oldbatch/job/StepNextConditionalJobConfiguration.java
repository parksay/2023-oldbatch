package com.learning.oldbatch.job;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
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
public class StepNextConditionalJobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextConditionalJob() {
        return this.jobBuilderFactory.get("stepNextConditionalJob")
            .start(conditionalJobStep01())  // conditionalJobStep01 을 시작한다
                .on("FAILED")     // conditionalJobStep0101 의 결과 실행 상태가 FAILED  경우
                .to(conditionalJobStep03())     // conditionalJobStep03 으로 이동한다
                .on("*")        // conditionalJobStep03 의 실행 결과 상태에 상관없이
                .end()      // 작업 flow 를 종료한다
            .from(conditionalJobStep01())       // 다시 conditionalJobStep01 로 돌아와서
                .on("*")    // conditionalJobStep01 의 실행 결과 상태와 상관없이 (앞에서 FAILED 인 경우는 처리했기 떄문에 그 외 나머지 상태를 서술)
                .to(conditionalJobStep02())     // conditionalJobStep02 로 이동한다
                .next(conditionalJobStep03())       // conditionalJobStep02 가 정상적으로 종료되면 conditionalJobStep03 으로 이동한다
                .on("*")    // conditionalJobStep03 의 실행 결과 상태에 상관없이
                .end()  // 작업을 종료한다
            .end()
            .build();
    }

    public Step conditionalJobStep01() {
        return this.stepBuilderFactory.get("conditionalStep01")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>> this is conditionalStep01");
                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public Step conditionalJobStep02() {
        return this.stepBuilderFactory.get("conditionalStep02")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>> this is conditionalStep02");
                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public Step conditionalJobStep03() {
        return this.stepBuilderFactory.get("conditionalStep03")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>> this is conditionalStep03");
                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
