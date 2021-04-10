package com.gofin.park;

import com.gofin.park.exception.ParkingLotException;
import com.gofin.park.manager.ParkingManager;
import com.gofin.park.manager.ParkingManagerImpl;
import com.gofin.park.service.ParkService;
import com.gofin.park.service.impl.ParkServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, ParkingLotException {

        ParkingManager manager = new ParkingManagerImpl();
        ParkService parkService = new ParkServiceImpl();
        manager.setService(parkService);

        //String fileName = args[0];
        String fileName = "parking_lot_file_inputs.txt";
        FileReader fileReader;
        BufferedReader bufferedReader;

        fileReader = new FileReader(new File(fileName));
        bufferedReader = new BufferedReader(fileReader);
        String command;

        while ((command = bufferedReader.readLine()) != null) {
            manager.executeCommand(command);

        }


    }
}
