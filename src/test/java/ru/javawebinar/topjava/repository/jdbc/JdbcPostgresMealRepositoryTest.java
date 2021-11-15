package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveJdbcProfileResolver;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(resolver = ActiveJdbcProfileResolver.class)
public class JdbcPostgresMealRepositoryTest extends MealServiceTest {
}
