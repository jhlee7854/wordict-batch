package kr.pe.jady.store.batch.web.management.log.service;

import kr.pe.jady.store.model.log.UserTransactionLog;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
public interface UserTransactionLogService {
    Iterable<UserTransactionLog> findAll();
}
