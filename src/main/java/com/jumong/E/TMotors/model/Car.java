package com.jumong.E.TMotors.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @Min(value = 1900, message = "Year must be greater than 1900")
    @Max(value = 2100, message = "Year must be less than 2100")
    private Integer year;


    @DecimalMin(value = "0.0", message = "Price must be positive")
    private Double price;

    private Integer mileage;

    @NotBlank(message = "Color is required")
    private String color;

    @NotBlank(message = "Engine is required")
    private String engine;

    private Transmission transmission;

    private FuelType fuelType;
    private DriveTrain driveTrain;

    @Column(unique = true)
    @Pattern(regexp = "^[A-Z0-9]{17}$", message = "Invalid VIN format")
    private String vin;

    @Enumerated(EnumType.STRING)
    private CarType type;


    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @ElementCollection
    @Size(max = 10, message = "Maximum 10 images allowed")
    private List<String> images = new ArrayList<>();

    private String description;

    private String features;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

