package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.LocationRepository;
import org.springframework.stereotype.Service;

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
}
