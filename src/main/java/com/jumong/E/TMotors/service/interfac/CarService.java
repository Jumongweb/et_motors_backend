package com.jumong.E.TMotors.service.interfac;

import com.jumong.E.TMotors.dto.request.CarRequest;
import com.jumong.E.TMotors.dto.request.UpdateCarRequest;
import com.jumong.E.TMotors.dto.response.CarResponse;
import com.jumong.E.TMotors.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {

    CarResponse addCar(CarRequest carRequest, String actorEmail) throws IOException;

    List<CarResponse> getAllCars();

    CarResponse getCarById(Long id);

//    CarResponse updateCar(Long id, UpdateCarRequest updateCarRequest);

    void deleteCar(Long carId);

    List<Car> getAvailableCars();

    List<Car> getCarsByType(String type);

    List<Car> getCarsByPriceRange(Double min, Double max);

    List<Car> getCarsByFuelType(String fuelType);

    List<Car> getCarsByTransmission(String transmission);

    List<Car> getCarsByDriveTrain(String driveTrain);

    List<CarResponse> searchCarByNameModelMakeOrDescription(String value);

}
