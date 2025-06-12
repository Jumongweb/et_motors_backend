package com.jumong.E.TMotors.repository;

import com.jumong.E.TMotors.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByType(CarType type);

    List<Car> findByPriceBetween(Double minPrice, Double maxPrice);

    @Query("SELECT c FROM Car c WHERE c.isAvailable = true")
    List<Car> findAllAvailable();

    List<Car> findByFuelType(FuelType fuelType);

    List<Car> findByTransmission(Transmission transmission);

    List<Car> findByDriveTrain(DriveTrain driveTrain);

    @Query("SELECT c FROM Car c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.make) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.model) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.features) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Car> searchCarsBy(String value);
}
