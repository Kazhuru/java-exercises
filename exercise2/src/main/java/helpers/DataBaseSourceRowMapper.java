package helpers;

import models.DataBaseSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseSourceRowMapper implements RowMapper<DataBaseSource> {

    @Override
    public DataBaseSource mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        DataBaseSource dataBaseSource = new DataBaseSource();

        dataBaseSource.setUrl(resultSet.getString("url"));
        dataBaseSource.setDriverName(resultSet.getString("driverName"));
        dataBaseSource.setUserName(resultSet.getString("userName"));
        dataBaseSource.setPassword(resultSet.getString("password"));
        dataBaseSource.setRegionId(resultSet.getString("regionId"));

        return dataBaseSource;
    }
}
