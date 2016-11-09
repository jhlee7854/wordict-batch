package kr.pe.jady.store.batch.job.mapper.log;

import kr.pe.jady.store.batch.system.annotation.LogReadMapper;
import kr.pe.jady.store.model.summary.DailyTransactionSummary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@Repository
@LogReadMapper
public interface UserTransactionLogReadMapper {
    List<DailyTransactionSummary> getDailyTransactionSummary(String basicDate);
}
