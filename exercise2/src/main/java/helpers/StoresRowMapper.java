package helpers;

import models.Store;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StoresRowMapper implements RowMapper<Store> {

    @Override
    public List<Store> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        List<Store> stores = new LinkedList<>();

        while (resultSet.next()) {
            stores.add(new Store(
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("regionId")));
        }

        return stores;
    }
}
