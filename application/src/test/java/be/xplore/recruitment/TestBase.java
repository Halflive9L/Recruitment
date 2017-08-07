package be.xplore.recruitment;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DbUnitConfiguration(databaseConnection = "dataSource")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
@Rollback
public abstract class TestBase {

    @Autowired
    protected TestRestTemplate restTemplate;

}
