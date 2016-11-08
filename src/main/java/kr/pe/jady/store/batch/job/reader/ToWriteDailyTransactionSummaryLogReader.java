package kr.pe.jady.store.batch.job.reader;

import kr.pe.jady.store.batch.job.mapper.log.UserTransactionLogReadMapper;
import kr.pe.jady.store.model.summary.DailyTransactionSummary;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jhlee7854 on 2016. 11. 3..
 */
@Component
@StepScope
public class ToWriteDailyTransactionSummaryLogReader implements ItemReader<DailyTransactionSummary> {
    @Autowired
    private UserTransactionLogReadMapper userTransactionLogReadMapper;
    @Value("#{jobParameters['inputDate']}")
    private String inputDate;
    private String basicDate;

    private List<DailyTransactionSummary> items;

    @Override
    public DailyTransactionSummary read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (items == null) setItems(inputDate);
        if (!items.isEmpty()) {
            DailyTransactionSummary item = items.remove(0);
            item.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(basicDate));
            return item;
        }
        return null;
    }

    private void setItems(String inputDate) throws java.text.ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if (inputDate != null && !inputDate.isEmpty()) {
            calendar.setTime(sdf.parse(inputDate));
        } else {
            calendar.setTime(new Date());
        }
        calendar.add(Calendar.DATE, -1);
        basicDate = sdf.format(calendar.getTime());
        items = userTransactionLogReadMapper.getDailyTransactionSummary(basicDate);
    }
}
