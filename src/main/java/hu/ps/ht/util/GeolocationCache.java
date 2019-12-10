package hu.ps.ht.util;

import hu.ps.ht.enumerated.GeoConstant;
import hu.ps.ht.enumerated.Region;
import hu.ps.ht.pojo.GeoLocation;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeolocationCache {

    public static Map<String, GeoLocation> locationMap = null;

    public static void startupCache() {

        try {

            locationMap = HttpClient.downloadGeolocations();
        } catch (Exception ex) {
            Logger.getLogger(HttpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Double[] getGeoPointArrayByRegion(Region region) {

        System.out.println("GEOLOCATION PARAMETER: " + region.toString());

        if (locationMap != null && locationMap.containsKey(region.toString())) {
            GeoLocation locationQueried = locationMap.get(region.toString());
            System.out.println("geoloc name: " + locationQueried.getName());
            System.out.println("GETLAT: " + locationQueried.getLat());
            System.out.println("GETLON: " + locationQueried.getLon());
            return new Double[]{locationQueried.getLat(), locationQueried.getLon()};
        }

        return GeoConstant.DEFAULT_GEO_LOC_POINT_BUDAPEST;
    }

}
