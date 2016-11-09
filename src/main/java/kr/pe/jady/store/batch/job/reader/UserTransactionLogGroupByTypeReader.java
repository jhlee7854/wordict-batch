package kr.pe.jady.store.batch.job.reader;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;
import kr.pe.jady.store.batch.system.util.DateUtil;
import kr.pe.jady.store.model.log.QUserTransactionLog;
import kr.pe.jady.store.model.summary.DailyTransactionSummary;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jhlee7854 on 2016. 11. 3..
 */
@Component
@StepScope
public class UserTransactionLogGroupByTypeReader implements ItemReader<DailyTransactionSummary> {
    @PersistenceContext(unitName = "readLog")
    private EntityManager entityManager;
    @Value("#{jobParameters['inputDate']}")
    private String inputDate;
    private Calendar basicDate;

    private List<DailyTransactionSummary> items;

    @Override
    public DailyTransactionSummary read() throws ParseException {
        if (items == null) setItems(inputDate);
        if (!items.isEmpty()) {
            DailyTransactionSummary item = items.remove(0);
            item.setDate(basicDate.getTime());
            return item;
        }
        return null;
    }

    private void setItems(String inputDate) throws ParseException {
        basicDate = DateUtil.convertDateStringToCalendar(inputDate);
        basicDate.add(Calendar.DATE, -1);

        QUserTransactionLog log = QUserTransactionLog.userTransactionLog;
        JPAQuery query = new JPAQuery(entityManager);

        items = query.from(log)
                .where(new BooleanBuilder().and(log.created.between(DateUtil.setStartTime(basicDate).getTime(), DateUtil.setEndTime(basicDate).getTime())))
                .groupBy(log.type)
                .list(Projections.fields(DailyTransactionSummary.class, log.type, log.type.count().as("count"), log.amount.sum().as("amount")));
    }
}