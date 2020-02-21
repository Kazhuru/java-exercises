package repository;

import helpers.DataBaseSourceRowMapper;
import helpers.StoresRowMapper;
import models.DataBaseSource;
import models.Store;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StoreRepository {

    private DataSource dataSource;

    public List<Store> getAllStoresByRegionalDataSource(DataBaseSource dataBaseSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(setUpDynamicallyDataSource(dataBaseSource));

        String regionId = dataBaseSource.getRegionId();

        String query = "SELECT * FROM Stores WHERE regionId = ?";
        List<Store> stores = jdbcTemplate.queryForObject(
                query,
                new Object[] { regionId },
                new StoresRowMapper());

        return dataBaseSource;
    }

    private DataSource setUpDynamicallyDataSource(DataBaseSource dataBaseSource) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataBaseSource.getDriverName());
        dataSource.setUrl(dataBaseSource.getUrl());
        dataSource.setUsername(dataBaseSource.getUserName());
        dataSource.setPassword(dataBaseSource.getPassword());

        return dataSource;
    }
}
