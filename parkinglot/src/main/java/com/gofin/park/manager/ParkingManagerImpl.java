package com.gofin.park.manager;

import com.gofin.park.constant.ParkingLotConstant;
import com.gofin.park.exception.ParkingLotException;
import com.gofin.park.model.Car;
import com.gofin.park.service.AbstractService;
import com.gofin.park.service.ParkService;
import com.gofin.park.util.CommandValidatorUtil;

public class ParkingManagerImpl implements ParkingManager {

    private ParkService parkService;

    public void setParkService(ParkService parkService) {
        this.parkService = parkService;
    }

    public void setService(AbstractService service) {
        if (service instanceof ParkService)
            this.parkService = (ParkService) service;
    }

    @Override
    public void executeCommand(String commandInput) throws ParkingLotException {
        String[] commandToken = commandInput.split(" ");
        String command = commandToken[0];
        Car car;

        if (!CommandValidatorUtil.isValidCommand(command)) {
            System.out.println(ParkingLotConstant.INVALID_COMMAND);
        } else {
            switch (command) {

                case ParkingLotConstant.CREATE_PARKING_LOT:
                    int capacity = Integer.parseInt(commandToken[1]);
                    parkService.createParkingLot(capacity);
                    break;

                case ParkingLotConstant.PARK:
                    car = new Car(commandToken[1], "");
                    parkService.parkCar(car);
                    break;

                case ParkingLotConstant.LEAVE:
                    int hours = Integer.parseInt(commandToken[2]);
                    car = new Car(commandToken[1], "");
                    parkService.unPark(car, hours);
                    break;

                case ParkingLotConstant.STATUS:
                    parkService.getParkingLotStatus();
                    break;

                default:
                    System.out.println(ParkingLotConstant.INVALID_COMMAND);

            }
        }
    }
}
