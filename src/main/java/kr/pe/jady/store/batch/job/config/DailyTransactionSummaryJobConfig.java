package kr.pe.jady.store.batch.job.config;

import kr.pe.jady.store.batch.system.util.SimpleJobParametersIncrementer;
import kr.pe.jady.store.model.summary.DailyTransactionSummary;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@Configuration
@EnableBatchProcessing
public class DailyTransactionSummaryJobConfig {
    public static final String JOB_NAME = "dailyTransactionSummaryJob";
    public static final String STEP_NAME = "dailyTransactionSummaryStep";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemReader<DailyTransactionSummary> toWriteDailyTransactionSummaryLogReader;

    @Autowired
    private ItemWriter<DailyTransactionSummary> dailyTransactionSummaryWriter;

    @Bean
    public Job dailyTransactionSummaryJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(dailyTransactionSummaryStep())
                .build();
    }

    @Bean
    public Step dailyTransactionSummaryStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .<DailyTransactionSummary, DailyTransactionSummary>chunk(4)
                .reader(toWriteDailyTransactionSummaryLogReader)
                .writer(dailyTransactionSummaryWriter)
                .build();
    }
}
