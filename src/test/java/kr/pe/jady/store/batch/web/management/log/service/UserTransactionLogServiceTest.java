package kr.pe.jady.store.batch.web.management.log.service;

import kr.pe.jady.store.batch.config.spring.app.AppConfig;
import kr.pe.jady.store.model.log.UserTransactionLog;
import org.hamcrest.collection.IsIterableWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
public class UserTransactionLogServiceTest {
    @Autowired
    private UserTransactionLogService service;

    @Before
    public void setUp() {
        assertNotNull("테스트 대상 확인", service);
    }

    @Test
    public void testFindAll() {
        Iterable<UserTransactionLog> list = service.findAll();
        assertThat(list, IsIterableWithSize.<UserTransactionLog>iterableWithSize(5));
    }
}
