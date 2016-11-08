package kr.pe.jady.store.model.log;

import kr.pe.jady.store.code.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@Entity
@Table(name = "user_transaction_log", schema = "log")
public class UserTransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "stock_id")
    private Long stockId;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private BigDecimal amount;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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
}
