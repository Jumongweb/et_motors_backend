package com.jumong.E.TMotors.controller;


import com.jumong.E.TMotors.dto.request.CarRequest;
import com.jumong.E.TMotors.dto.request.UpdateCarRequest;
import com.jumong.E.TMotors.dto.response.CarResponse;
import com.jumong.E.TMotors.model.*;
import com.jumong.E.TMotors.service.interfac.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cars")
@CrossOrigin(origins = "*")
@Slf4j
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CarResponse> addCar(
            @RequestParam String name,
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam Integer year,
            @RequestParam Double price,
            @RequestParam Integer mileage,
            @RequestParam String color,
            @RequestParam String engine,
            @RequestParam Transmission transmission,
            @RequestParam FuelType fuelType,
            @RequestParam DriveTrain driveTrain,
            @RequestParam String vin,
            @RequestParam CarType type,
            @RequestParam(required = false, defaultValue = "true") Boolean isAvailable,
            @RequestParam String description,
            @RequestParam String features,
            @RequestParam(required = false) List<MultipartFile> images,
            Principal principal) throws IOException {
        CarRequest carRequest = new CarRequest();
        carRequest.setName(name);
        carRequest.setMake(make);
        carRequest.setModel(model);
        carRequest.setYear(year);
        carRequest.setPrice(price);
        carRequest.setMileage(mileage);
        carRequest.setColor(color);
        carRequest.setEngine(engine);
        carRequest.setTransmission(transmission);
        carRequest.setFuelType(fuelType);
        carRequest.setDriveTrain(driveTrain);
        carRequest.setVin(vin);
        carRequest.setType(type);
        carRequest.setIsAvailable(isAvailable);
        carRequest.setDescription(description);
        carRequest.setFeatures(features);
        carRequest.setImages(images);

        String actorEmail = principal.getName();
        log.info("-------> email: {}", email);
        return new ResponseEntity<>(carService.addCar(carRequest, actorEmail), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarResponse>> searchCars(
            @RequestParam(required = false) String query) {
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.ok(carService.getAllCars());
        }
        return ResponseEntity.ok(carService.searchCarByNameModelMakeOrDescription(query));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @Valid @RequestBody UpdateCarRequest updateCarRequest) {
//        return ResponseEntity.ok(carService.updateCar(id, updateCarRequest));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    // Search endpoints
    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return ResponseEntity.ok(carService.getAvailableCars());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Car>> getCarsByType(@PathVariable String type) {
        return ResponseEntity.ok(carService.getCarsByType(type));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Car>> getCarsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        return ResponseEntity.ok(carService.getCarsByPriceRange(min, max));
    }

    @GetMapping("/fuel-type/{fuelType}")
    public ResponseEntity<List<Car>> getCarsByFuelType(@PathVariable String fuelType) {
        return ResponseEntity.ok(carService.getCarsByFuelType(fuelType));
    }

    @GetMapping("/transmission/{transmission}")
    public ResponseEntity<List<Car>> getCarsByTransmission(@PathVariable String transmission) {
        return ResponseEntity.ok(carService.getCarsByTransmission(transmission));
    }

    @GetMapping("/drive-train/{driveTrain}")
    public ResponseEntity<List<Car>> getCarsByDriveTrain(@PathVariable String driveTrain) {
        return ResponseEntity.ok(carService.getCarsByDriveTrain(driveTrain));
    }

}