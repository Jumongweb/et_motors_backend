package com.jumong.E.TMotors.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jumong.E.TMotors.model.CarType;
import com.jumong.E.TMotors.model.DriveTrain;
import com.jumong.E.TMotors.model.FuelType;
import com.jumong.E.TMotors.model.Transmission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class CarRequest {

    private String name;

    private String make;

    private String model;

    private Integer year;

    private Double price;

    private Integer mileage;

    private String color;

    private String engine;

    private Transmission transmission;

    private FuelType fuelType;
    private DriveTrain driveTrain;

    private String vin;

    private CarType type;

    private Boolean isAvailable = true;

    private String description;

    private String features;

    @JsonIgnore
    private List<MultipartFile> images;
}
