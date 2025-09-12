package com.BatteryMonitorServer;

import java.io.*;
import org.yaml.snakeyaml.Yaml;
import java.util.Map;

public class PythonOuput {
    private Device device_info;
    private String python_directory;

    PythonOuput( Device device ) {
        device_info = device;
        Yaml yaml = new Yaml();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/config.yaml");
        Map<String, Object> yaml_map = yaml.load(inputStream);
        python_directory = (String) yaml_map.get( "python directory" );

    } // end PythonOuput

    public void print_device_info( String battery_Pct, String token ) {

        String device_name = device_info.get_device_name(token);

        ProcessBuilder pb = new ProcessBuilder("python", "print_output.py", device_name, battery_Pct );
        pb.directory(new File(python_directory));
        pb.inheritIO();
        try {
            Process process = pb.redirectErrorStream(true).start();

            int exitCode = process.waitFor();
            /*
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Python output: " + line);
            }
             */

            if ( exitCode != 0 ) {
                System.out.println("Process exited with code: " + exitCode);
            } // end if()

        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

    } // end print_device_info()




}
