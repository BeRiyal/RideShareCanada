package com.example.ridesharecanada.model.API;

import com.google.android.gms.maps.model.LatLng;
        import java.util.ArrayList;
        import java.util.List;

public class PolylineUtils {

    public static List<LatLng> decodePolyline(String encodedPolyline) {
        List<LatLng> polyPoints = new ArrayList<>();
        int index = 0;
        int len = encodedPolyline.length();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;

            do {
                b = encodedPolyline.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);

            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;

            do {
                b = encodedPolyline.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);

            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng point = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            polyPoints.add(point);
        }

        return polyPoints;
    }
}
