package kr.pe.jady.store.batch.job.writer;

import kr.pe.jady.store.batch.job.mapper.summary.DailyTransactionSummaryWriteMapper;
import kr.pe.jady.store.model.summary.DailyTransactionSummary;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@Component
public class DailyTransactionSummaryWriter implements ItemWriter<DailyTransactionSummary> {
    @Autowired
    private DailyTransactionSummaryWriteMapper dailyTransactionSummaryWriteMapper;

    @Override
    public void write(List<? extends DailyTransactionSummary> items) throws Exception {
        for (DailyTransactionSummary item : items) {
            dailyTransactionSummaryWriteMapper.saveDailyTransactionSummary(item);
        }
    }
}
