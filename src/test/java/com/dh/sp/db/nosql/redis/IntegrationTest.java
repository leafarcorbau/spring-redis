package com.dh.sp.db.nosql.redis;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(
        initializers = IntegrationTest.Initializer.class)
public abstract class IntegrationTest {

    static class Initializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {

        static GenericContainer<?> redis = new GenericContainer("redis")
                .withExposedPorts(6379);

        private static void startContainers() {
            Startables.deepStart(Stream.of(redis)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "dh.redis.host", redis.getHost(),
                    "dh.redis.port", redis.getMappedPort(6379).toString()
            );
        }

        @Override
        public void initialize(
                ConfigurableApplicationContext applicationContext) {

            startContainers();

            ConfigurableEnvironment environment =
                    applicationContext.getEnvironment();

            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration()
            );

            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}
