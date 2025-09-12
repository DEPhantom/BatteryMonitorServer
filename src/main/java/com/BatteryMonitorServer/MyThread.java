package com.BatteryMonitorServer;

import java.io.IOException;
import java.util.Vector;

public class MyThread implements Runnable {

    private Device device_info;

    MyThread( Device device ) {
        device_info = device;
    } // end Device

    @Override
    public void run() {
        TCPServer testserver = new TCPServer(device_info);

        try {
            testserver.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // end run()

} // end MyThread
