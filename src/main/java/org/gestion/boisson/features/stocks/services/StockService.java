package org.gestion.boisson.features.stocks.services;

import org.gestion.boisson.features.stocks.entities.Stock;

public interface StockService {
    org.gestion.boisson.features.stocks.entities.Stock getByBoissonName(String boisson);
    org.gestion.boisson.features.stocks.entities.Stock updateBoissonStock(String boisson);

    void save(Stock stock);
}
