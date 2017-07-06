package io.egen.repository;

import io.egen.entity.Alert;
import io.egen.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Vehicle> findAll() {
        TypedQuery<Vehicle> query = entityManager.createNamedQuery("Vehicle.findAll", Vehicle.class);
        return query.getResultList();
    }

    public Vehicle findOne(String vId) {
        return entityManager.find(Vehicle.class, vId);
    }

    public Vehicle create(Vehicle vehicle) {
        setPriority(vehicle);
        entityManager.persist(vehicle);
        return vehicle;
    }

    public Vehicle update(Vehicle vehicle) {
        setPriority(vehicle);
        return entityManager.merge(vehicle);
    }

    private void setPriority(Vehicle vehicle) {

        if(vehicle.getEngineRpm() != null && vehicle.getRedlineRpm() != null) {
            if (vehicle.getEngineRpm() > vehicle.getRedlineRpm()) {
                Alert alert = new Alert();
                alert.setVehicle(vehicle);
                alert.setPriority("HIGH");
                entityManager.merge(alert);
            }
        }
        else if(vehicle.getFuelVolume() != null && vehicle.getMaxFuelVolume() != null) {
            if(vehicle.getFuelVolume() < (0.1 * vehicle.getMaxFuelVolume())) {
                Alert alert = new Alert();
                alert.setVehicle(vehicle);
                alert.setPriority("MEDIUM");
                entityManager.merge(alert);
            }
        }
        else if(vehicle.getTires() != null){
                if((vehicle.getTires().getFrontLeft() < 32 || vehicle.getTires().getFrontLeft() > 36) &&
                (vehicle.getTires().getFrontRight() < 32 || vehicle.getTires().getFrontRight() > 36) &&
                (vehicle.getTires().getRearLeft() < 32 || vehicle.getTires().getRearLeft() > 36) &&
                (vehicle.getTires().getRearRight() < 32 || vehicle.getTires().getRearRight() > 36)) {
                    Alert alert = new Alert();
                    alert.setVehicle(vehicle);
                    alert.setPriority("LOW");
                    entityManager.merge(alert);
                }
        }
        else if(vehicle.getEngineCoolantLow() != null && vehicle.getCheckEngineLightOn() != null) {
            if (vehicle.getEngineCoolantLow() == Boolean.TRUE || vehicle.getCheckEngineLightOn() == Boolean.TRUE) {
                Alert alert = new Alert();
                alert.setVehicle(vehicle);
                alert.setPriority("LOW");
                entityManager.merge(alert);
            }
        }

    }

    public void delete(Vehicle vehicle) {
        entityManager.remove(vehicle);
    }
}
