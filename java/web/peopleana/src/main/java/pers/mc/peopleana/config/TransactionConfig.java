package pers.mc.peopleana.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author michael
 * @date 2018/04/14
 */
@Configuration
@EnableTransactionManagement
@Slf4j
public class TransactionConfig {

    @Bean
    public Object testBean(PlatformTransactionManager txManager) {
        log.info("Tests TxManager Info : " + txManager.getClass().getName());
        return new Object();
    }
}
