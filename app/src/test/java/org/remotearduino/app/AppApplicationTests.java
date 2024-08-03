package org.remotearduino.app;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Disabled
@SpringBootTest
@Import(TestcontainersConfiguration.class)
class AppApplicationTests {

	@Test
	@Disabled
	void contextLoads() {
	}

}
