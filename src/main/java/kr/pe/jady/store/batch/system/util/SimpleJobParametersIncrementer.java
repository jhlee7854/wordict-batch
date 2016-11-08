package kr.pe.jady.store.batch.system.util;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.util.Date;

/**
 * Created by jhlee7854 on 2016. 11. 4..
 */
public class SimpleJobParametersIncrementer implements JobParametersIncrementer {
    @Override
    public JobParameters getNext(JobParameters parameters) {
        if (parameters==null || parameters.isEmpty()) {
            return new JobParametersBuilder().addDate("run.date", new Date()).toJobParameters();
        }
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        parameters.getParameters().forEach((key, value) -> {
            jobParametersBuilder.addParameter(key, value);
        });
        return jobParametersBuilder.addDate("run.date", new Date()).toJobParameters();
    }
}
