package kr.pe.jady.store.batch.job.reader;

import kr.pe.jady.store.batch.config.spring.app.*;
import kr.pe.jady.store.batch.config.spring.batch.BatchConfig;
import kr.pe.jady.store.batch.system.util.DateUtil;
import kr.pe.jady.store.model.summary.DailyTransactionSummary;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jhlee7854 on 2016. 11. 4..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, StepScopeTestExecutionListener.class}) // StepScope 컴포넌트를 테스트할 때는 테스트 ExecutionListener를 추가하자.
public class ToWriteDailyTransactionSummaryLogReaderTest {
    @Autowired
    private ToWriteDailyTransactionSummaryLogReader reader;

    @BeforeClass
    public static void setUpClass() throws NamingException {
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("jdbc/batch", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
        SimpleNamingContextBuilder.getCurrentContextBuilder().bind("jdbc/readLog", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
        SimpleNamingContextBuilder.getCurrentContextBuilder().bind("jdbc/writeLog", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
        SimpleNamingContextBuilder.getCurrentContextBuilder().bind("jdbc/readSummary", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
        SimpleNamingContextBuilder.getCurrentContextBuilder().bind("jdbc/writeSummary", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
    }

    @Before
    public void setUp() {
        assertNotNull("테스트 대상 확인", reader);
    }

    public StepExecution getStepExecution() {
        return MetaDataInstanceFactory.createStepExecution();
    }

    public StepExecution getStepExecution(JobParameters jobParameters) {
        StepExecution execution = jobParameters == null ? getStepExecution() : MetaDataInstanceFactory.createStepExecution(jobParameters);
        // 테스트를 위해 execution 컨텍스트를 통해 공유할 자료가 있다면 여기서 추가한다.
        return execution;
    }

    @Test
    public void testRead() throws Exception {
        int itemReadCount = getItemReadCount(null);
        assertEquals("예상대로라면 읽은 아이템의 갯수는 2개이다.", 2, itemReadCount);
    }

    @Test
    public void testReadWithParameter() throws Exception {
        int itemReadCount = getItemReadCount(new JobParametersBuilder().addString("inputDate", DateUtil.getYesterdayDateString()).toJobParameters());
        assertEquals("예상대로라면 읽은 아이템의 갯수는 1개이다.", 1, itemReadCount);
    }

    private int getItemReadCount(JobParameters jobParameters) throws Exception {
        return StepScopeTestUtils.doInStepScope(getStepExecution(jobParameters), new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int count = 0;
                DailyTransactionSummary item = null;
                while ((item = reader.read()) != null) {
                    assertNotNull("각 아이템의 date항목은 값이 존재해야 한다.", item.getDate());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    assertEquals("각 아이템의 date항목은 어제 일자 이거나 파라메터 일자보다 하루 전 이어야한다.", DateUtil.calculateDateString(jobParameters == null ? sdf.format(new Date()) : jobParameters.getString("inputDate"), -1), sdf.format(item.getDate()));
                    count++;
                }

                return count;
            }
        });
    }
}
