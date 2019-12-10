package hu.ps.ht.service;

import hu.ps.ht.enumerated.Region;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class RegionService {

    public boolean checkIfRegionExists(String input) {

        try {
            Region.valueOf(input);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
