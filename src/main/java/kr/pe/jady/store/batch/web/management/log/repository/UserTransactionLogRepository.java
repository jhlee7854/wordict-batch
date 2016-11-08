package kr.pe.jady.store.batch.web.management.log.repository;

import kr.pe.jady.store.model.log.UserTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
@Repository
public interface UserTransactionLogRepository extends JpaRepository<UserTransactionLog, Long> {
}
