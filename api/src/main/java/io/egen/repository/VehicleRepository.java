package io.egen.repository;

import io.egen.entity.Vehicle;

import java.util.List;

/**
 * Created by marew on 01-07-2017.
 */
public interface VehicleRepository {
    List<Vehicle> findAll();

    Vehicle findOne(String vId);

    Vehicle create(Vehicle vehicle);

    Vehicle update(Vehicle vehicle);

    void delete(Vehicle v);
}
