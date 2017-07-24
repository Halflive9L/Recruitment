package be.xplore.recruitment;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import net.minidev.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.sql.DataSourceDefinition;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DataSourceDefinition(className = "dataSource", name = "dataSource", url = "jdbc:h2:mem:testdb", user = "sa", password = "", portNumber = 9191)
@DbUnitConfiguration(databaseConnection = "dataSource")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class, DbUnitTestExecutionListener.class})
@Transactional
@Rollback
public abstract class TestBase {
    @LocalServerPort
    protected int port = 9191;

    @Autowired
    protected TestRestTemplate restTemplate;


    @Ignore
    protected abstract JSONObject getJsonTestObject();

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT)
    public abstract void testPOST();

    @Test
    public abstract void testGetById();

    @Test
    public abstract void testGetByParam();
}
