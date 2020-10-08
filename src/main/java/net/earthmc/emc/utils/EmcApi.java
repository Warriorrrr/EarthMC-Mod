package net.earthmc.emc.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import net.earthmc.emc.ModConfig;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class EmcApi
{
    public static JsonArray getTownless()
    {
        try
        {
            final URL url = new URL("http://earthmc-api.herokuapp.com/townlessplayers");

            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Getting the response code
            final int responsecode = conn.getResponseCode();

            if (responsecode == 200)
            {
                StringBuilder inline = new StringBuilder();
                final Scanner scanner = new Scanner(url.openStream());

                // Write all the JSON data into a string using a scanner
                while (scanner.hasNext())
                {
                    inline.append(scanner.nextLine());
                }

                // Close the scanner
                scanner.close();

                // Using the JSON simple library parse the string into a json object
                final JsonParser parse = new JsonParser();
                return (JsonArray) parse.parse(inline.toString());
            }
        }
        catch (final Exception exc)
        {
            return new JsonArray();
        }

        return new JsonArray();
    }

    public static JsonArray getNearby(ModConfig config)
    {
        try
        {
            final URL url = new URL("http://earthmc-api.herokuapp.com/onlineplayers/" + config.nearby.playerName + "/nearby/" + config.nearby.xRadius + "/" + config.nearby.zRadius);

            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Getting the response code
            final int responsecode = conn.getResponseCode();

            if (responsecode == 200)
            {
                StringBuilder inline = new StringBuilder();
                final Scanner scanner = new Scanner(url.openStream());

                // Write all the JSON data into a string using a scanner
                while (scanner.hasNext())
                {
                    inline.append(scanner.nextLine());
                }

                // Close the scanner
                scanner.close();

                // Using the JSON simple library parse the string into a json object
                final JsonParser parse = new JsonParser();
                return (JsonArray) parse.parse(inline.toString());
            }
        }
        catch (final Exception exc)
        {
            return new JsonArray();
        }

        return new JsonArray();
    }
}