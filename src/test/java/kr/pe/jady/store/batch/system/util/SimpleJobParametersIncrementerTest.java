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
        assertEquals("", new Date().getTime(), jobParameters.getDate(RUN_DATE_PARAMETER).getTime(), 1000);
    }

    @Test
    public void testGetNextWithEmptyParameter() {
        JobParameters jobParameters = new SimpleJobParametersIncrementer().getNext(new JobParameters());
        assertEquals("", new Date().getTime(), jobParameters.getDate(RUN_DATE_PARAMETER).getTime(), 1000);
    }

    @Test
    public void testGetNextWithSomeParameter() {
        JobParameters jobParameters = new SimpleJobParametersIncrementer().getNext(new JobParametersBuilder().addString("stuff", "thing").toJobParameters());
        assertEquals("", new Date().getTime(), jobParameters.getDate(RUN_DATE_PARAMETER).getTime(), 1000);
        assertEquals("", "thing", jobParameters.getString("stuff"));
    }
}
