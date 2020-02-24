package service;

import models.TransactionProcess;

public interface ProcessRepository {
    TransactionProcess saveProcess(String name);
}
