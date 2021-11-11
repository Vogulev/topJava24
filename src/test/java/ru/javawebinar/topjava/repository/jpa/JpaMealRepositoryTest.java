package ru.javawebinar.topjava.repository.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceTest;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaMealRepositoryTest extends MealServiceTest {

}