package com.gofin.park.manager;

import com.gofin.park.exception.ParkingLotException;
import com.gofin.park.service.AbstractService;
import com.gofin.park.service.ParkService;

public interface ParkingManager {

     public void setService(AbstractService service);

     public void executeCommand(String command) throws ParkingLotException;
}
