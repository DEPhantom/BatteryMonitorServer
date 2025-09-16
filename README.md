# BatteryMonitorServer

![Logo](https://github.com/DEPhantom/BatteryMonitorServer/blob/main/img/cli-picture.jpg)

> This project must be used in conjunction with [the repository](https://github.com/DEPhantom/BatteryMonitorServer).

用來監控手機電量的Server.

## Dependencies 
* java
* maven
* python 3
* rich

## Download
Download the latest pre-release from the [releases page](https://github.com/DEPhantom/BatteryMonitor/releases/tag/Pre-release).
Or
Build the project by yourself.

```sh
https://github.com/DEPhantom/BatteryMonitorServer.git
```

## Get started

1. Navigate to
`src/main/resources/config/config.yaml`
to configure the server IP and the absolute path to the Python script.
By default, the Python script is usually located in:
```plaintext BatteryMonitorServer/src/ └── print_output.py ```
3. Go to your project settings in the Google Firebase Console,
navigate to the Service Accounts tab, and generate a new private key.
Place the downloaded key file in:
```plaintext src/main/resources/ └── FCM_key/ └── yourkey.json ```
4. Run
```sh
java -jar BatteryMonitorServer-1.0.jar
```
