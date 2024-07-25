package org.example.hostelbooking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hostelbooking.entity.BookingData;
import org.example.hostelbooking.entity.RegistrationData;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    private final BookingDataService bookingDataService;

    private final RegistrationDataService registrationDataService;

    public String createCsv() {

        String path = "src/main/resources/infoFiles/data.csv";

        try (FileWriter writer = new FileWriter(path)) {
            writer.write("\"Id\",\"UserId\"");
            writer.write("\n");
            List<RegistrationData> registrationDataList = registrationDataService.findAll();
            for (RegistrationData registrationData : registrationDataList) {
                writer.write("\"" + registrationData.getId() + "\",\"" + registrationData.getUserId() + "\"");
                writer.write("\n");
            }

            writer.write("\"Id\",\"UserId\",\"BookingIn\",\"BookingOut\"");
            writer.write("\n");
            List<BookingData> bookingDataList = bookingDataService.findAll();
            for (BookingData bookingData : bookingDataList) {
                writer.write("\"" + bookingData.getId() + "\",\"" + bookingData.getUserId() + "\",\"" + bookingData.getInBooking() + "\",\"" + bookingData.getOutBooking() + "\"");
                writer.write("\n");
            }

            log.info("Csv created");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}

