package hu.ps.ht.util;

import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.enumerated.WeeklyPattern;
import java.util.List;

public class EventManagerUtil {

    public static List<EventDTO> convertDTOsWeeklyPatternAttributesTo3CharNames(List<EventDTO> eventdtoList) {

        String[] daysPatternArray = WeeklyPattern.SEVEN_DAYS_ARRAY;
        for (EventDTO eventDTO : eventdtoList) {

            String daycodes = eventDTO.getDayOfWeek();
            int lengthOfString = daycodes.length();
            String formattedDayList = (lengthOfString == 0) ? " not available" : " ";

            for (int i = 0; i < lengthOfString; i++) {
                if (daycodes.charAt(i) != 'X') {
                    formattedDayList += daysPatternArray[i] + " ";
                }
            }

            eventDTO.setDayOfWeek(formattedDayList);
        }
        return eventdtoList;
    }

    public static Double[] convertStringInputGeolocationToDouble(String geolocationString) {

        if (geolocationString == null) {
            return null;
        }

        String[] geolocSplit = geolocationString.split(",");
        if (geolocSplit.length != 2) {
            return null;
        }
        geolocSplit[0] = geolocSplit[0].replaceAll("[^\\d.]", "");
        geolocSplit[1] = geolocSplit[1].replaceAll("[^\\d.]", "");

        try {
            Double[] geolocationArray
                    = new Double[]{Double.parseDouble(geolocSplit[0]), Double.parseDouble(geolocSplit[1])};
            return geolocationArray;
        } catch (NumberFormatException e) {
            System.out.println("invalid input - geolocation");
        }

        return null;
    }

    public static String switchWeeklyPatternContants(String pattern) {
        switch (pattern) {
            case "1":
                return WeeklyPattern.ALWAYS_VACANT;

            case "2":
                return WeeklyPattern.VACANT_EXPECT_MONDAYS;

            case "3":
                return WeeklyPattern.WEEKDAYS_ONLY;

            case "4":
                return WeeklyPattern.WEEKENDS_ONLY;

            default:
                throw new AssertionError("invalid input*******************");
        }

    }

}
