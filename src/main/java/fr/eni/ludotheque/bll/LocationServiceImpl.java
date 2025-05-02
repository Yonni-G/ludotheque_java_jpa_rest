package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Bill;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.LocationRepository;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.temporal.ChronoUnit;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void endLocation(Location location) {
        if (location.getBill() == null && location.getReturningDateAt() != null) {
            Bill bill = new Bill();

            long locationDurationInDays = ChronoUnit.DAYS.between(location.getStartingDateAt(), location.getReturningDateAt());

            float billAmount = location.getDayPrice() * locationDurationInDays;

            bill.setAmount(billAmount);

            location.setBill(bill);
        }
    }
}
