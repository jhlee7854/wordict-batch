package kr.pe.jady.store.batch.web.management.log.service;

import kr.pe.jady.store.batch.web.management.log.repository.UserTransactionLogRepository;
import kr.pe.jady.store.model.log.UserTransactionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
@Service
public class UserTransactionLogServiceImpl implements UserTransactionLogService {
    @Autowired
    private UserTransactionLogRepository userTransactionLogRepository;

    @Override
    public Iterable<UserTransactionLog> findAll() {
        return userTransactionLogRepository.findAll();
    }
}
