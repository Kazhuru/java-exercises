package services;

import models.Store;

import java.util.List;

public interface StoreService {
    List<Store> getStoresByRegion(String regionId);
}
