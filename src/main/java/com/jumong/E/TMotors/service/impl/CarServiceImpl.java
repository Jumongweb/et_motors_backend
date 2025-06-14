package com.jumong.E.TMotors.service.impl;


import com.jumong.E.TMotors.dto.request.CarRequest;
import com.jumong.E.TMotors.dto.request.UpdateCarRequest;
import com.jumong.E.TMotors.dto.response.CarResponse;
import com.jumong.E.TMotors.exception.CarException;
import com.jumong.E.TMotors.mapper.CarMapper;
import com.jumong.E.TMotors.model.*;
import com.jumong.E.TMotors.repository.CarRepository;
import com.jumong.E.TMotors.service.CloudinaryService;
import com.jumong.E.TMotors.service.interfac.CarService;
import com.jumong.E.TMotors.service.interfac.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return carMapper.toCarResponses(cars);
    }

    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarException("Car not found with id: " + id));
        return carMapper.toCarResponse(car);
    }

    public CarResponse addCar(CarRequest carRequest, String actorEmail) throws IOException {
        User user = userService.findByEmail(actorEmail);
        log.info("------> logged in user ---> {}", user);
        List<MultipartFile> images = carRequest.getImages();
        images.forEach(this::validateFile);

        List<String> imageUrls = new ArrayList<>();
        if (!images.isEmpty()) {
            for (MultipartFile eachCarImage : images) {
                String imageUrl = cloudinaryService.uploadFile(eachCarImage);
                imageUrls.add(imageUrl);
            }
        }
        Car car = carMapper.toCarEntity(carRequest);
        car.setImages(imageUrls);
        car.setUser(user);
        Car savedCar = carRepository.save(car);
        return carMapper.toCarResponse(savedCar);
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        if (!Arrays.asList("image/jpeg", "image/png").contains(file.getContentType())) {
            throw new IllegalArgumentException("Only JPG/PNG files are allowed");
        }
    }

//    public CarResponse updateCar(Long id, UpdateCarRequest updateCarRequest) {
//        Car foundCar = getCarById(id);
//        Car updatedCar = carMapper.toCarEntity(updateCarRequest);
//        Car savedCar = carRepository.save(foundCar);
//        return carMapper.toCarResponse(savedCar);
//    }

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    public List<Car> getAvailableCars() {
        return carRepository.findAllAvailable();
    }

    public List<Car> getCarsByType(String type) {
        return carRepository.findByType(CarType.valueOf(type.toUpperCase()));
    }

    public List<Car> getCarsByPriceRange(Double min, Double max) {
        return carRepository.findByPriceBetween(min, max);
    }

    @Override
    public List<Car> getCarsByFuelType(String fuelType) {
        return carRepository.findByFuelType(FuelType.valueOf(fuelType.toUpperCase()));
    }


    public List<Car> getCarsByTransmission(String transmission) {
        return carRepository.findByTransmission(Transmission.valueOf(transmission.toUpperCase()));
    }

    public List<Car> getCarsByDriveTrain(String driveTrain) {
        return carRepository.findByDriveTrain(DriveTrain.valueOf(driveTrain.toUpperCase()));
    }

    @Override
    public List<CarResponse> searchCarByNameModelMakeOrDescription(String value) {
        List<Car> cars = carRepository.searchCarsBy(value);
        return carMapper.toCarResponses(cars);
    }


}
