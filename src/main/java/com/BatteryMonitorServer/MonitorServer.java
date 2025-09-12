package com.BatteryMonitorServer;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessagingException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.yaml.snakeyaml.Yaml;
import java.util.Map;

public class MonitorServer {

    private String server_ip;

    MonitorServer() {
        Yaml yaml = new Yaml();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/config.yaml");
        Map<String, Object> yaml_map = yaml.load(inputStream);
        server_ip = (String) yaml_map.get( "server ip" );

    } // end MonitorServer()

    public void fcm_send_message( Device device_info ) {
        int count = 0;
        String current_token = "";
        try {

            while ( count < device_info.device_name.size() ) {
                current_token = device_info.device_token.get(count);
                FirebasCloudMessaging fcm = new FirebasCloudMessaging();
                // 呼叫發送推播
                fcm.sendToToken(current_token, server_ip);

                count = count+1;

            } // end while()


        } catch (FirebaseMessagingException e) {
            e.printStackTrace(); // 或寫 log、顯示錯誤訊息等
        }

    } // end fcm_send_message()

    public void initialize_fcm() {
        try {
            // 初始化 Firebase
            // FileInputStream serviceAccount = new FileInputStream("FCM_key/battery-monitor-5eefa-firebase-adminsdk-fbsvc-974a953035.json");
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("FCM_key/battery-monitor-5eefa-firebase-adminsdk-fbsvc-974a953035.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);


        } catch (IOException e) {
            e.printStackTrace(); // 或寫 log、顯示錯誤訊息等
        }

    } // end initialize_fcm()

    public void run() {

        Device device_info = new Device();

        Thread testserver = new Thread( new MyThread(device_info) );
        testserver.start();

        initialize_fcm();
        // 等一拍 再執行
        fcm_send_message( device_info );


    } // end run()


}
