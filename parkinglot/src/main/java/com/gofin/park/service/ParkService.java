package com.gofin.park.service;

import com.gofin.park.exception.ParkingLotException;
import com.gofin.park.model.Car;

public interface ParkService extends AbstractService {

    public void createParkingLot(int capacity) throws ParkingLotException;


    public void parkCar(Car car) throws ParkingLotException;


    public void unPark(Car car, int hours) throws ParkingLotException;


    public void getParkingLotStatus() throws ParkingLotException;

}
