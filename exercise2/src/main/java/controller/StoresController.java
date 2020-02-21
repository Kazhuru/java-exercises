package controller;

import models.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.StoreService;

import java.util.List;

@RestController
@RequestMapping("exercise2-api/")
public class StoresController {

    StoreService storeService;

    public StoresController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("{regionId}/stores")
    public ResponseEntity<List<Store>> getRegionalStores(@PathVariable String regionId) {
        List<Store> stores = storeService.getStoresByRegion(regionId);

        return ResponseEntity.ok().body(stores);
    }

}
