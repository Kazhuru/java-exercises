package repository;

import helpers.DataBaseSourceRowMapper;
import models.DataBaseSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class DataSourceRepository {

    private DataSource dataSource;

    public DataSourceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataBaseSource getDataSourceByRegion(String regionId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM DataSources WHERE regionId = ?";
        DataBaseSource dataBaseSource = jdbcTemplate.queryForObject(
                query,
                new Object[] { regionId },
                new DataBaseSourceRowMapper());

        return dataBaseSource;
    }
}
