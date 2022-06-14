package bg.manhattan.battleships.service;

import bg.manhattan.battleships.model.service.ShipFireServiceModel;
import bg.manhattan.battleships.model.service.ShipServiceModel;
import bg.manhattan.battleships.model.view.ShipListViewModel;

public interface ShipService {
    void addShip(ShipServiceModel shipModel);

    ShipListViewModel getAllShips();

    void fire(ShipFireServiceModel fireModel);
}
