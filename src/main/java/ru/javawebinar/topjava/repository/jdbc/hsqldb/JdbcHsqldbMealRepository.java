package ru.javawebinar.topjava.repository.jdbc.hsqldb;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Repository
public class JdbcHsqldbMealRepository extends JdbcMealRepository {

    public JdbcHsqldbMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected Date getCorrectDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


}
