import com.gofin.park.constant.ParkingLotConstant;
import com.gofin.park.exception.ParkingLotException;
import com.gofin.park.manager.ParkingManager;
import com.gofin.park.manager.ParkingManagerImpl;
import com.gofin.park.model.Car;
import com.gofin.park.service.ParkService;
import com.gofin.park.service.impl.ParkServiceImpl;
import com.gofin.park.util.ParkingFareUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream orignalOut = System.out;


    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStream() {
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void testCreateParkingLot() throws ParkingLotException {

        int parkingCapacity = 8;
        ParkService parkService = new ParkServiceImpl();
        parkService.createParkingLot(parkingCapacity);


        assertEquals(String.format(ParkingLotConstant.PARKING_LOT_CREATED_MSG, parkingCapacity), getOutContentString());

    }

    @Test
    public void testParkCar() throws ParkingLotException {

        int parkingCapacity = 8;
        Car car = new Car("KA-01-HH-1234", "");
        ParkService parkService = new ParkServiceImpl();
        parkService.createParkingLot(parkingCapacity);
        parkService.parkCar(car);

        String expectedResult = String.join("", String.format(ParkingLotConstant.PARKING_LOT_CREATED_MSG, parkingCapacity), String.format(ParkingLotConstant.ALLOCATED_SLOT_NUMBER_MSG, 1));
        assertEquals(expectedResult, getOutContentString());

    }


    @Test
    public void testUnParkCar() throws ParkingLotException {

        int parkingCapacity = 8;
        Car car = new Car("KA-01-HH-1234", "");
        ParkService parkService = new ParkServiceImpl();
        parkService.createParkingLot(parkingCapacity);
        parkService.parkCar(car);
        parkService.unPark(car, 4);

        String expectedResult = String.join("", String.format(ParkingLotConstant.PARKING_LOT_CREATED_MSG, parkingCapacity), String.format(ParkingLotConstant.ALLOCATED_SLOT_NUMBER_MSG, 1), String.format(ParkingLotConstant.REGISTRATION_NUMBER_FREE_MSG, car.getRegistrationNo(), 1, ParkingFareUtil.getCarParkingBill(4)));
        assertEquals(expectedResult, getOutContentString());

    }


    @Test
    public void testGetParkingLotStatus() throws ParkingLotException {

        int parkingCapacity = 8;
        Car car1 = new Car("KA-01-HH-1234", "");
        Car car2 = new Car("KA-01-HH-9999", "");
        ParkService parkService = new ParkServiceImpl();
        parkService.createParkingLot(parkingCapacity);
        parkService.parkCar(car1);
        parkService.parkCar(car2);
        parkService.getParkingLotStatus();

        String expectedResult = String.join("", String.format(ParkingLotConstant.PARKING_LOT_CREATED_MSG, parkingCapacity), String.format(ParkingLotConstant.ALLOCATED_SLOT_NUMBER_MSG, 1), String.format(ParkingLotConstant.ALLOCATED_SLOT_NUMBER_MSG, 2),String.format(ParkingLotConstant.SLOT_NO_REGISTRATION_NO_MSG),"1 KA-01-HH-1234", "2 KA-01-HH-9999");
        assertEquals(expectedResult, getOutContentString());

    }


    @Test
    public void testParkingLot() throws IOException, ParkingLotException {

        ParkingManager manager = new ParkingManagerImpl();
        ParkService parkService = new ParkServiceImpl();
        manager.setService(parkService);

        String fileName = "src//test//resources//parking_lot_file_inputs_test.txt";
        FileReader fileReader;
        BufferedReader bufferedReader;

        fileReader = new FileReader(new File(fileName));
        bufferedReader = new BufferedReader(fileReader);
        String command;

        while ((command = bufferedReader.readLine()) != null) {
            manager.executeCommand(command);
        }

        assertEquals(getResultString(), getOutContentString());

    }


    public String getResultString() {

        return String.join("", "Created parking lot with 6 Slots", "Allocated slot number: 1", "Allocated slot number: 2", "Allocated slot number: 3", "Allocated slot number: 4", "Allocated slot number: 5", "Allocated slot number: 6", "Registration number KA-01-HH-3141 with Slot Number 6 is free with Charge 30", "Slot No. Registration No.", "1 KA-01-HH-1234", "2 KA-01-HH-9999", "3 KA-01-BB-0001", "4 KA-01-HH-7777", "5 KA-01-HH-2701", "Allocated slot number: 6", "Sorry, parking lot is full", "Registration number KA-01-HH-1234 with Slot Number 1 is free with Charge 30", "Registration number KA-01-BB-0001 with Slot Number 3 is free with Charge 50", "Registration number DL-12-AA-9999 not found", "Allocated slot number: 1", "Allocated slot number: 3", "Sorry, parking lot is full", "Slot No. Registration No.", "1 KA-09-HH-0987", "2 KA-01-HH-9999", "3 CA-09-IO-1111", "4 KA-01-HH-7777", "5 KA-01-HH-2701", "6 KA-01-P-333");
    }

    private String getOutContentString() {

        return outContent.toString().replace(System.getProperty("line.separator"), "");
    }
}


