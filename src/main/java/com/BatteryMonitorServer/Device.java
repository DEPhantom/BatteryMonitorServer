package com.BatteryMonitorServer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class Device {
    public Vector<String> device_name;
    public Vector<String> device_token;

    Device() {
        device_name = new Vector<>();
        device_token = new Vector<>();

        read_device_info();

    } // end Device

    public void read_device_info() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            InputStream jsonFile = getClass().getClassLoader().getResourceAsStream("device/device_info.json");
            JsonNode root = mapper.readTree(jsonFile);

            if (root.isArray()) {
                for (JsonNode node : root) {
                    String token = node.get("token").asText();
                    String name = node.get("name").asText();
                    device_name.add(name);
                    device_token.add(token);

                } // end for()

            } // end if()

        } catch (IOException e) {
            System.err.println("❌ read JSON occur error : " + e.getMessage());
        } // end catch


    } // end read_device_info()

    public String get_token( String name ) {
        boolean find = false;
        int count = 0;
        while( count < device_name.size() && !find ) {
            if ( name.equals( device_name.get( count ) ) ) {
                find = true;
            } // end if()

            count = count+1;

        } // end while()

        if ( find ) {
            return device_token.get( count-1 );
        } // if()
        else {
            return "No token";
        } // else()


    } // get_token()

    public String get_device_name( String token ) {
        boolean find = false;
        int count = 0;
        while( count < device_token.size() && !find ) {
            if ( token.equals( device_token.get( count ) ) ) {
                find = true;
            } // end if()

            count = count+1;

        } // end while()

        if ( find ) {
            return device_name.get( count-1 );
        } // if()
        else {
            return "No device";
        } // else()

    } // get_device_name()

} // end Device
