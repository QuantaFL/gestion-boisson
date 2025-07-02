package org.gestion.boisson.features.stocks.services;

import org.gestion.boisson.features.stocks.entities.Stock;

public interface StockInterface {
    Stock getByBoissonName(String boisson);
    Stock updateBoissonStock(String boisson);
}
