package com.gofin.park.service.impl;

import com.gofin.park.constant.ParkingLotConstant;
import com.gofin.park.exception.ParkingLotException;
import com.gofin.park.model.Car;
import com.gofin.park.service.ParkService;
import com.gofin.park.util.ParkingFareUtil;

import java.util.*;

public class ParkServiceImpl implements ParkService {

    final Map<Integer, Car> parkingSlotMap = new LinkedHashMap<>();
    List<Integer> slots=new LinkedList<>();

    @Override
    public void createParkingLot(int parkingCapacity) throws ParkingLotException {
        try {
            for (int i = ParkingLotConstant.ONE; i <= parkingCapacity; i++) {
                parkingSlotMap.put(i, null);
                slots.add(i);
            }
            System.out.println(String.format(ParkingLotConstant.PARKING_LOT_CREATED_MSG, parkingCapacity));

        } catch (Exception ex) {
            throw new ParkingLotException("Exception Occured While Creating Parking Lot", ex);
        }

    }

    @Override
    public void parkCar(Car car) throws ParkingLotException {

        try {

            int slot = getParkingSlot();
            if (slot > ParkingLotConstant.ZERO) {
                parkingSlotMap.put(slot, car);
                System.out.println(String.format(ParkingLotConstant.ALLOCATED_SLOT_NUMBER_MSG, slot));
            } else {
                System.out.println(ParkingLotConstant.PARKING_LOT_FULL_MSG);
            }

        } catch (Exception ex) {
            throw new ParkingLotException("Exception occured while parking car", ex);
        }

    }

    @Override
    public void unPark(Car car, int hours) throws ParkingLotException {
        try {
            int parkingSlotUsed = leaveParkingLot(car);

            if (parkingSlotUsed > 0) {
                System.out.println(String.format(ParkingLotConstant.REGISTRATION_NUMBER_FREE_MSG, car.getRegistrationNo(), parkingSlotUsed, ParkingFareUtil.getCarParkingBill(hours)));

            } else {
                System.out.println(String.format(ParkingLotConstant.REGISTRATION_NUMBER_NOT_FOUND_MSG, car.getRegistrationNo()));
            }

        } catch (Exception ex) {
            throw new ParkingLotException("Exception occured while un-parking car", ex);
        }
    }


    @Override
    public void getParkingLotStatus() throws ParkingLotException {
        try {
            System.out.println(String.format(ParkingLotConstant.SLOT_NO_REGISTRATION_NO_MSG));
            parkingSlotMap.entrySet().stream().filter(entry -> entry.getValue() != null).forEach((entry) -> System.out.println(entry.getKey() + " " + entry.getValue().getRegistrationNo()));
        } catch (Exception ex) {
            throw new ParkingLotException("Exception occured in parking car status", ex);
        }
    }


    private int getParkingSlot() throws ParkingLotException {
        int parkingSlot = ParkingLotConstant.ZERO;
        try {
            if (!parkingSlotMap.isEmpty()) {
/*                Optional<Map.Entry<Integer, Car>> availableSlotEntry = parkingSlotMap.entrySet().stream().filter(entry -> entry.getValue() == null).findFirst();*/
                parkingSlot= slots.get(0);
                slots.remove(0);



/*                if (availableSlotEntry.isPresent()) {
                    parkingSlot = availableSlotEntry.get().getKey();
                }*/
            }

        } catch (Exception ex) {
            throw new ParkingLotException("Exception occured while getting parking slot", ex);
        }
        return parkingSlot;
    }


    private int leaveParkingLot(Car car) throws ParkingLotException {
        int parkingSlotUsed = ParkingLotConstant.ZERO;

        try {
            if (!parkingSlotMap.isEmpty()) {
                Set<Map.Entry<Integer, Car>> entrySet = parkingSlotMap.entrySet();
                for (Map.Entry<Integer, Car> entry : entrySet) {
                    if (entry.getValue() != null && entry.getValue().getRegistrationNo().equals(car.getRegistrationNo())) {
                        parkingSlotUsed = entry.getKey();
                        parkingSlotMap.replace(entry.getKey(), null);
                    }
                }
            }
            slots.add(parkingSlotUsed);

        } catch (Exception ex) {
            throw new ParkingLotException("Exception occured in leave  parking lot", ex);
        }
        return parkingSlotUsed;
    }

}
