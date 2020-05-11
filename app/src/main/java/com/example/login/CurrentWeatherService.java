package com.example.login;

import android.app.Activity;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CurrentWeatherService {

    private static final String TAG = CurrentWeatherService.class.getSimpleName();

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String CURRENT_WEATHER_TAG = "CURRENT_WEATHER";
    private static final String API_KEY = "3dd03ad57b0c558dc34416679712afab";

    private RequestQueue queue;

    public CurrentWeatherService(@NonNull final Activity activity) {
        queue = Volley.newRequestQueue(activity.getApplicationContext());
    }

    public interface CurrentWeatherCallback {
        @MainThread
        void onCurrentWeather(@NonNull final CurrentWeather currentWeather);

        @MainThread
        void onError(@Nullable Exception exception);
    }

    public void getCurrentWeather(@NonNull final String locationName, @NonNull final CurrentWeatherCallback callback) {
        final String url = String.format("%s?q=%s&appId=%s", URL, locationName, API_KEY);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final JSONObject currentWeatherJSONObject = new JSONObject(response);
                            final JSONArray weather = currentWeatherJSONObject.getJSONArray("weather");
                            final JSONObject weatherCondition = weather.getJSONObject(0);
                            final String locationName = currentWeatherJSONObject.getString("name");
                            final int conditionId = weatherCondition.getInt("id");
                            final String conditionName = weatherCondition.getString("main");
                            final double tempKelvin = currentWeatherJSONObject.getJSONObject("main").getDouble("temp");
                            final double temp_min = currentWeatherJSONObject.getJSONObject("main").getDouble("temp_min");
                            final double temp_max = currentWeatherJSONObject.getJSONObject("main").getDouble("temp_max");
                            final int humidity = currentWeatherJSONObject.getJSONObject("main").getInt("humidity");
                            final int pressure = currentWeatherJSONObject.getJSONObject("main").getInt("pressure");
                            final double windspeed = currentWeatherJSONObject.getJSONObject("wind").getDouble("speed");

                            final CurrentWeather currentWeather = new CurrentWeather(locationName, conditionId, conditionName, tempKelvin, pressure, humidity, windspeed, temp_max, temp_min);



                            callback.onCurrentWeather(currentWeather);
                        } catch (JSONException e) {
                            callback.onError(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });
        stringRequest.setTag(CURRENT_WEATHER_TAG);
        queue.add(stringRequest);
    }

    public void cancel() {
        queue.cancelAll(CURRENT_WEATHER_TAG);
    }
}

