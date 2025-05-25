package com.example.webflux_learning.tests.section2;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "sec=section2",
        "logging.level.org.springframework.r2dbc=DEBUG"
})
public abstract class AbstractTest {
}
