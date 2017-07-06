package io.egen.service;

import io.egen.entity.Vehicle;
import io.egen.exception.BadRequestException;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository repository;

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicle findOne(String vId) {
        Vehicle existing = repository.findOne(vId);

        if(existing == null)
            throw new ResourceNotFoundException("Vehicle with vin " + vId + " doesn't exist.");

        return existing;
    }

    @Transactional()
    public List<Vehicle> create(List<Vehicle> vehicle) {
        List<Vehicle> v1 = new ArrayList<Vehicle>();

        for(Vehicle v : vehicle){
            Vehicle existing = repository.findOne(v.getVin());
            if(existing != null)
                throw new BadRequestException("Vehicle with vin " + v.getVin() + " already exist.");

            v1.add(repository.create(v));

        }

        return v1;
    }

    @Transactional()
    public List<Vehicle> update(List<Vehicle> vehicle) {

        List<Vehicle> v1 = new ArrayList<Vehicle>();

        for(Vehicle v : vehicle){
            Vehicle existing = repository.findOne(v.getVin());
            if(existing != null) {
                existing = updateVehicle(existing, v);
                System.out.println(existing);
                v1.add(repository.update(existing));
            }
            else {
                v1.add(repository.create(v));
            }
        }

        return v1;
    }

    private Vehicle updateVehicle(Vehicle existing, Vehicle v) {
        if(v.getMake() != null) existing.setMake(v.getMake());
        if(v.getModel() != null) existing.setModel(v.getModel());
        if(v.getYear() != null) existing.setYear(v.getYear());
        if(v.getLatitude() != null) existing.setLatitude(v.getLatitude());
        if(v.getLongitude() != null) existing.setLongitude(v.getLongitude());
        if(v.getTimestamp() != null) existing.setTimestamp(v.getTimestamp());
        if(v.getLastServiceDate() != null) existing.setLastServiceDate(v.getLastServiceDate());
        if(v.getFuelVolume() != null) existing.setFuelVolume(v.getFuelVolume());
        if(v.getRedlineRpm() != null) existing.setRedlineRpm(v.getRedlineRpm());
        if(v.getMaxFuelVolume() != null) existing.setMaxFuelVolume(v.getMaxFuelVolume());
        if(v.getSpeed() != null) existing.setSpeed(v.getSpeed());
        if(v.getEngineHp() != null) existing.setEngineHp(v.getEngineHp());
        if(v.getCheckEngineLightOn() != null) existing.setCheckEngineLightOn(v.getCheckEngineLightOn());
        if(v.getEngineCoolantLow() != null) existing.setEngineCoolantLow(v.getEngineCoolantLow());
        if(v.getCruiseControlOn() != null) existing.setCruiseControlOn(v.getCruiseControlOn());
        if(v.getEngineRpm() !=  null) existing.setEngineRpm(v.getEngineRpm());
        if(v.getTires() != null) existing.setTires(v.getTires());

        return existing;
    }

    @Transactional()
    public void delete(String vId) {
        Vehicle existing = repository.findOne(vId);
        if(existing == null)
            throw new BadRequestException("Vehicle with vin " + vId + " doesn't exist.");

        repository.delete(existing);
    }
}
