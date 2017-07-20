package be.xplore.recruitment;

import be.xplore.recruitment.web.api.ProspectController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecruitmentApplicationTests {

	@Autowired
	private ProspectController prospectController;

	@Test
	public void contextLoads() {
		assertThat(prospectController).isNotNull();
	}
}
