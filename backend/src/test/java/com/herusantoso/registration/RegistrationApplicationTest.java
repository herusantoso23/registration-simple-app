package com.herusantoso.registration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationApplication.class)
public class RegistrationApplicationTest {

    @Test(expected = Test.None.class)
    public void contextLoads() {
        RegistrationApplication.main(new String[]{
                "--spring.main.web-environment=false",
                "--spring.autoconfigure.exclude=none",
        });
    }

}
