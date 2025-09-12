package com.BatteryMonitorServer;

import java.io.*;
import java.net.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        MonitorServer server = new MonitorServer();
        server.run();

    } // end main()

} // end Main