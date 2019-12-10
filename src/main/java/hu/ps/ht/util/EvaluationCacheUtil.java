package hu.ps.ht.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;

@Singleton
public class EvaluationCacheUtil {

    Map<String, StringBuffer> evalCache = new HashMap<>();

    public void addScoreToEvalLogByGuideName(String travelername, String guidename, Integer score) {

        StringBuffer logEntry = new StringBuffer();
        logEntry.append("\n")
                .append(LocalDateTime.now())
                .append(" user: ")
                .append(travelername)
                .append(" awarded ")
                .append(score)
                .append(" point(s).\n");

        if (!evalCache.containsKey(guidename)) {
            evalCache.put(guidename, new StringBuffer().append(logEntry));
        } else {
            evalCache.put(guidename, evalCache.get(guidename).append(logEntry));
        }
    }

    public String getEvalLogByGuideName(String guidename) {
        if (evalCache.containsKey(guidename)) {
            return evalCache.get(guidename).toString();
        } else {
            return "NO ENTRY";
        }
    }

}
