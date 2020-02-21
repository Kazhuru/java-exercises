package services;

import models.DataBaseSource;

public interface DataSourceService {
    DataBaseSource getDataSourceByRegion(String regionId);
}
