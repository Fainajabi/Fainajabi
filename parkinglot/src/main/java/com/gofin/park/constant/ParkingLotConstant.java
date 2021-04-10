package com.gofin.park.constant;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotConstant {

    private ParkingLotConstant() {
    }

    public static final String CREATE_PARKING_LOT = "create_parking_lot";
    public static final String PARK = "park";
    public static final String LEAVE = "leave";
    public static final String STATUS = "status";

    public static final int BILL_PER_HOUR = 10;
    public static final int INITIAL_FIXED_BILL = 10;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;


    public static final String INVALID_COMMAND = "Invalid Command";
    public static final String PARKING_LOT_FULL_MSG = "Sorry, parking lot is full";
    public static final String PARKING_LOT_CREATED_MSG = "Created parking lot with %s Slots";
    public static final String ALLOCATED_SLOT_NUMBER_MSG = "Allocated slot number: %s";
    public static final String REGISTRATION_NUMBER_NOT_FOUND_MSG = "Registration number %s not found";
    public static final String REGISTRATION_NUMBER_FREE_MSG = "Registration number %s with Slot Number %s is free with Charge %s";
    public static final String SLOT_NO_REGISTRATION_NO_MSG = "Slot No. Registration No.";

    public static List<String> COMMAND_INPUT = new ArrayList<>();

    static {
        COMMAND_INPUT.add(CREATE_PARKING_LOT);
        COMMAND_INPUT.add(PARK);
        COMMAND_INPUT.add(LEAVE);
        COMMAND_INPUT.add(STATUS);

    }


    public static List<String> getCommandInput() {
        return COMMAND_INPUT;
    }

    public static void setCommandInput(List<String> commandInput) {
        ParkingLotConstant.COMMAND_INPUT = commandInput;
    }
}
