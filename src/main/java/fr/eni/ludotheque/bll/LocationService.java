package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Location;

public interface LocationService {
    Location addLocation(Location location);
    void endLocation(Location location);
}
