package com.gofin.park.util;

import com.gofin.park.constant.ParkingLotConstant;

public class ParkingFareUtil {

    private ParkingFareUtil() {
    }

    public static int getCarParkingBill(int hours) {
        int parkingBill = ParkingLotConstant.ZERO;
        if (hours > ParkingLotConstant.ZERO) {
            parkingBill = ParkingLotConstant.INITIAL_FIXED_BILL;
            parkingBill = hours <= ParkingLotConstant.TWO ? parkingBill
                    : parkingBill + ParkingLotConstant.BILL_PER_HOUR * (hours - ParkingLotConstant.TWO);
        }
        return parkingBill;
    }
}
