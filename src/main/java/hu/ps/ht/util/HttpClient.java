package hu.ps.ht.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.ps.ht.pojo.GeoLocation;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class HttpClient {

    @PostConstruct
    private void startup() {
        try {
            System.out.println("***********************ALTATAS***************");
            System.out.println("***********************ALTATAS***************");
            System.out.println("***********************ALTATAS***************");
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(HttpClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("***********************ALTATAS VEGE***************");
        System.out.println("***********************ALTATAS VEGE***************");
        System.out.println("***********************ALTATAS VEGE***************");

        GeolocationCache.startupCache();
    }

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static Map<String, GeoLocation> downloadGeolocations() throws Exception {

        HttpClient obj = new HttpClient();

        Map<String, GeoLocation> fromSetToMap = new HashMap<>();
        try {
            System.out.println("Send Http GET request");
            String json = obj.sendGet();
            ObjectMapper mapper = new ObjectMapper();
            Set<GeoLocation> backtoSet
                    = PoJoJsonConverterUtil.convertArrayToSet(mapper.readValue(json, GeoLocation[].class));
            System.out.println("***************************");
            System.out.println("***************************");
            for (GeoLocation geoLocation : backtoSet) {
                System.out.println(geoLocation.toString());
                fromSetToMap.put(geoLocation.getName(), geoLocation);

            }

        } finally {
            obj.close();
        }
        return fromSetToMap;
    }

    private void close() throws IOException {
        httpClient.close();
    }

    private String sendGet() throws Exception {

        HttpGet request = new HttpGet("http://hidegver.nhely.hu/hellotourist/regiongeolocation.json");

        // add request headers
        request.addHeader("custom-key", "bh10");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {

                String result = EntityUtils.toString(entity);
                return result;
            }

        }
        return null;
    }

}
