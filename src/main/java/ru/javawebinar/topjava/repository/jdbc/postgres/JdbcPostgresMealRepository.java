package ru.javawebinar.topjava.repository.jdbc.postgres;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository;

import java.time.LocalDateTime;

@Repository
public class JdbcPostgresMealRepository extends JdbcMealRepository {
    public JdbcPostgresMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected LocalDateTime getCorrectDate(LocalDateTime dateTime) {
        return dateTime;
    }
}
