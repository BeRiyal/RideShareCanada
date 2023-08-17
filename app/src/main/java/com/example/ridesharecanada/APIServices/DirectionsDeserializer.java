package com.example.ridesharecanada.APIServices;

import android.util.Log;

import com.example.ridesharecanada.model.API.DirectionsResponse;
import com.example.ridesharecanada.model.API.OverviewPolyline;
import com.example.ridesharecanada.model.API.Route;
import com.example.ridesharecanada.model.API.Step;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DirectionsDeserializer implements JsonDeserializer<DirectionsResponse> {

    @Override
    public DirectionsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        DirectionsResponse directionsResponse = new DirectionsResponse();
        JsonObject jsonObject = json.getAsJsonObject();
        Log.d("Riyal","in deserializer");
        // Process geocoded_waypoints if needed

        List<Route> routes = new ArrayList<>();
        if (jsonObject.has("routes")) {
            JsonElement routesElement = jsonObject.get("routes");
            if (routesElement.isJsonArray()) {
                for (JsonElement routeElement : routesElement.getAsJsonArray()) {
                    Route route = new Route();
                    JsonObject routeObject = routeElement.getAsJsonObject();

                    // Process bounds, copyrights, etc. if needed

                    // Process legs
                    if (routeObject.has("legs")) {
                        JsonElement legsElement = routeObject.get("legs");
                        if (legsElement.isJsonArray() && legsElement.getAsJsonArray().size() > 0) {
                            JsonObject legObject = legsElement.getAsJsonArray().get(0).getAsJsonObject(); // Assuming the first leg

                            // Process distance, duration, start_address, etc. if needed

                            // Process steps
                            if (legObject.has("steps")) {
                                JsonElement stepsElement = legObject.get("steps");
                                if (stepsElement.isJsonArray()) {
                                    List<Step> steps = new ArrayList<>();
                                    for (JsonElement stepElement : stepsElement.getAsJsonArray()) {
                                        Step step = new Step();
                                        JsonObject stepObject = stepElement.getAsJsonObject();

                                        // Process distance, duration, end_location, etc. if needed

                                        // Process polyline
                                        if (stepObject.has("polyline")) {
                                            JsonObject polylineObject = stepObject.get("polyline").getAsJsonObject();
                                            String polylinePoints = polylineObject.get("points").getAsString();

                                            OverviewPolyline overviewPolyline = new OverviewPolyline();
                                            overviewPolyline.setPoints(polylinePoints);

                                            step.setPolyline(overviewPolyline);
                                        }

                                        // Process other step properties if needed

                                        steps.add(step);
                                    }

                                    route.setSteps(steps);
                                }
                            }

                            // Process other leg properties if needed

                            routes.add(route);
                        }
                    }
                }
            }
        }

        directionsResponse.setRoutes(routes);
        Log.d("Riyal retunr from deserializer", String.valueOf(directionsResponse));
        return directionsResponse;
    }

    // You might need additional inner classes to represent nested JSON structures
}
