package org.gestion.boisson.features.stocks.dao;

import org.gestion.boisson.features.stocks.entities.Stock;

public interface StockDao {
    Stock findByBoissonId(Long boissonId);
    Stock save(Stock stock);
    Stock update(Stock stock);
    void deleteByBoissonId(Long boissonId);
    Stock findById(Long id);
}
