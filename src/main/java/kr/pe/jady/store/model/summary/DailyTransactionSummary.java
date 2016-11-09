package kr.pe.jady.store.model.summary;

import kr.pe.jady.store.code.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
public class DailyTransactionSummary {
    private Long id;
    private Date date;
    private TransactionType type;
    private Long count;
    private BigDecimal amount;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "DailyTransactionSummary{" +
                "id=" + id +
                ", date=" + date +
                ", type=" + type +
                ", count=" + count +
                ", amount=" + amount +
                ", created=" + created +
                '}';
    }
}
