package hu.ps.ht.enumerated;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mantis
 */
public enum Region {
    AGGTELEK("./aggtelek.jpg"),
    BALATON("./balaton.jpg"),
    BUDAPEST("./budapest.jpg"),
    HOLLOKO("./holloko.jpg"),
    HORTOBAGY("./hortobagy.jpg"),
    TOKAJ("./tokaj.jpg");

    public final String hyperLink;

    private Region(String hyperLink) {
        this.hyperLink = hyperLink;
    }

    public static List<String> getRegionImageLinks() {

        List<String> regionImageLink = new ArrayList<>();
        Region[] regionValues = Region.values();

        for (Region regionValue : regionValues) {
            regionImageLink.add(regionValue.hyperLink);
        }

        return regionImageLink;
    }

}
