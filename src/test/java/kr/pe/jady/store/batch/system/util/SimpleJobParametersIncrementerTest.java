package kr.pe.jady.store.batch.system.util;

import org.junit.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
public class SimpleJobParametersIncrementerTest {
    private static final String RUN_DATE_PARAMETER = "run.date";

    @Test
    public void testGetNextWithNullParameter() {
        JobParameters jobParameters = new SimpleJobParametersIncrementer().getNext(null);
        assertEquals("job 파라메터가 null이면 현재 일시를 job 파라메터로 추가한다.", new Date().getTime(), jobParameters.getDate(RUN_DATE_PARAMETER).getTime(), 1000);
    }

    @Test
    public void testGetNextWithEmptyParameter() {
        JobParameters jobParameters = new SimpleJobParametersIncrementer().getNext(new JobParameters());
        assertEquals("job 파라메터가 empty이면 현재 일시를 job 파라메터로 추가한다.", new Date().getTime(), jobParameters.getDate(RUN_DATE_PARAMETER).getTime(), 1000);
    }

    @Test
    public void testGetNextWithSomeParameter() {
        JobParameters jobParameters = new SimpleJobParametersIncrementer().getNext(new JobParametersBuilder().addString("stuff", "thing").toJobParameters());
        assertEquals("job 파라메터가 존재 하더라도 현재 일시를 job 파라메터로 추가한다.", new Date().getTime(), jobParameters.getDate(RUN_DATE_PARAMETER).getTime(), 1000);
        assertEquals("기 존재하는 job 파라메터는 그대로 존재해야 한다.", "thing", jobParameters.getString("stuff"));
    }
}
