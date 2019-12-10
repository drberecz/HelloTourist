package hu.ps.ht.util;

import hu.ps.ht.enumerated.Region;
import hu.ps.ht.service.RegionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class StringEnumMatcherUtil {

    @Inject
    RegionService regionService;

    public static final int MINIMUM_MATCHING_LENGTH = 4;

    public Region matchInputWithRegionEnums(String input) {

        String inputTrimmed = input.trim().toUpperCase();
        if (inputTrimmed != null && inputTrimmed.length() > MINIMUM_MATCHING_LENGTH) {
            inputTrimmed = inputTrimmed.substring(0, MINIMUM_MATCHING_LENGTH);
        }

        List<String> regionListAsString = generateRegionList();

        if (regionListAsString == null || regionListAsString.isEmpty()) {
            return null;
        }

        for (String regionStr : regionListAsString) {
            if (regionStr.contains(inputTrimmed)) {

                return Region.valueOf(regionStr);
            }
        }

        return null;
    }

    List<String> generateRegionList() {

        List<String> regionList = new ArrayList<>();
        Region[] regionArray = Region.values();

        for (Region region : regionArray) {
            regionList.add(region.toString());
        }
        return regionList;
    }

}
