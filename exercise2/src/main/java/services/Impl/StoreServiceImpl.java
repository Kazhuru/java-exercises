package services.Impl;

import models.DataBaseSource;
import models.Store;
import org.springframework.stereotype.Service;
import repository.DataSourceRepository;
import repository.StoreRepository;
import services.StoreService;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    DataSourceRepository dataSourceRepository;
    StoreRepository storeRepository;

    public StoreServiceImpl(DataSourceRepository dataSourceRepository,
                            StoreRepository storeRepository) {
        this.dataSourceRepository = dataSourceRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getStoresByRegion(String regionId) {
        DataBaseSource requestedDataBaseSource = dataSourceRepository.getDataSourceByRegion(regionId);
        return storeRepository.getAllStoresByRegionalDataSource(requestedDataBaseSource);
    }
}
