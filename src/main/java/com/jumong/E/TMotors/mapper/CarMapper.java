package com.jumong.E.TMotors.mapper;

import com.jumong.E.TMotors.dto.request.CarRequest;
import com.jumong.E.TMotors.dto.request.UpdateCarRequest;
import com.jumong.E.TMotors.dto.response.CarResponse;
import com.jumong.E.TMotors.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "images", ignore = true)
    Car toCarEntity(CarRequest carRequest);

    @Mapping(source = "id", target = "id")
    List<CarResponse> toCarResponses(List<Car> cars);

    @Mapping(source = "id", target = "id")
    CarResponse toCarResponse(Car savedCar);
}
