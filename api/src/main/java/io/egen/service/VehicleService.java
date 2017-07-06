package io.egen.service;

import io.egen.entity.Vehicle;

import java.util.List;

/**
 * Created by marew on 01-07-2017.
 */
public interface VehicleService {
    List<Vehicle> findAll();

    Vehicle findOne(String vId);

    List<Vehicle> create(List<Vehicle> vehicle);

    List<Vehicle> update(List<Vehicle> vehicle);

    void delete(String vId);
}
