package com.gofin.park.util;

import com.gofin.park.constant.ParkingLotConstant;

public class CommandValidatorUtil {

    private CommandValidatorUtil() {

    }

    public static boolean isValidCommand(String command) {
        return ParkingLotConstant.COMMAND_INPUT.contains(command);
    }
}
