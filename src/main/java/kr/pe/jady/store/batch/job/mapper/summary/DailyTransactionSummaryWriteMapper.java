package kr.pe.jady.store.batch.job.mapper.summary;

import kr.pe.jady.store.batch.system.annotation.SummaryWriteMapper;
import kr.pe.jady.store.model.summary.DailyTransactionSummary;
import org.springframework.stereotype.Repository;

/**
 * Created by jhlee7854 on 2016. 11. 3..
 */
@Repository
@SummaryWriteMapper
public interface DailyTransactionSummaryWriteMapper {
    int saveDailyTransactionSummary(DailyTransactionSummary item);
}
