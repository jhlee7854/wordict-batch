package kr.pe.jady.store.batch.job.config;

import kr.pe.jady.store.batch.config.spring.app.AppConfig;
import kr.pe.jady.store.batch.config.spring.app.DataSourceTestConfig;
import kr.pe.jady.store.batch.system.util.DateUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.NamingException;

import static org.junit.Assert.assertEquals;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class DailyTransactionSummaryJobTest {
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    @Qualifier(DailyTransactionSummaryJobConfig.JOB_NAME)
    Job job;

    @BeforeClass
    public static void setUpClass() throws NamingException {
        DataSourceTestConfig.buildNamingContext();
    }

    @Before
    public void setUp() {
        jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJob(job);
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        jobLauncherTestUtils.setJobRepository(jobRepository);
    }

    @Test
    public void testDailyTransactionSummaryStep() {
        JobExecution jobExecution = jobLauncherTestUtils.launchStep(DailyTransactionSummaryJobConfig.STEP_NAME);
        jobExecution.getStepExecutions().forEach(stepExecution -> {
            if (DailyTransactionSummaryJobConfig.STEP_NAME.equals(stepExecution.getStepName())) {
                assertEquals("step read 건수", 2, stepExecution.getReadCount());
                assertEquals("step write 건수", 2, stepExecution.getWriteCount());
            }
        });
    }

    @Test
    public void testDailyTransactionSummaryJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertEquals("job 수행 성공 여부", BatchStatus.COMPLETED, jobExecution.getStatus());
    }

    @Test
    public void testDailyTransactionSummaryStepWithParameter() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("inputDate", DateUtil.getYesterdayDateString()).toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchStep(DailyTransactionSummaryJobConfig.STEP_NAME, jobParameters);
        jobExecution.getStepExecutions().forEach(stepExecution -> {
            if (DailyTransactionSummaryJobConfig.STEP_NAME.equals(stepExecution.getStepName())) {
                assertEquals("step read 건수", 1, stepExecution.getReadCount());
                assertEquals("step write 건수", 1, stepExecution.getWriteCount());
            }
        });
    }

    @Test
    public void testDailyTransactionSummaryJobWithParameter() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("inputDate", DateUtil.getYesterdayDateString()).toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("job 수행 성공 여부", BatchStatus.COMPLETED, jobExecution.getStatus());
    }
}
