# mujRozhlas.cz downloader


![????](doc/img/01_audio_unavailable.png)

Autorská práva dovolující ponechat zajímavé a skvělé počiny __pouze 1 měsíc__ - obrovská škoda. Zazálohujte si pro vlastní potřebu audio soubory.

Utility downloads either whole series, if it is one or just one file otherwise. 

## Run via Docker


```shell
export FOLDER=${HOME}/mujrozhlas-download
mkdir -p ${FOLDER}
docker run --rm -v ${FOLDER}:/download bardolf/mujrozhlas --url.to.download='https://www.mujrozhlas.cz/cetba-na-pokracovani/pavel-sanajev-pochovejte-me-pod-podlahu'
```

debug

```shell
docker run --rm -v ${HOME}/muj-rozhlas-download:/download -p 5005:5005 --env JAVA_OPTS='-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005' bardolf/mujrozhlas --url.to.download='https://www.mujrozhlas.cz/cetba-na-pokracovani/pavel-sanajev-pochovejte-me-pod-podlahu' 
```

## Build Docker
 
```shell
docker build -t bardolf/mujrozhlas .
```

## Run via gradle


```shell
./gradlew bootRun --url.to.download='https://www.mujrozhlas.cz/cetba-na-pokracovani/pavel-sanajev-pochovejte-me-pod-podlahu'  
```

## Example 

```log
$ export FOLDER=${HOME}/mujrozhlas-download 
$ mkdir -p ${FOLDER} 
$ docker run --rm -v ${FOLDER}:/download bardolf/mujrozhlas --url.to.download='https://www.mujrozhlas.cz/cetba-na-pokracovani/pavel-sanajev-pochovejte-me-pod-podlahu'
 
Unable to find image 'bardolf/mujrozhlas:latest' locally
latest: Pulling from bardolf/mujrozhlas
f3ef4ff62e0d: Already exists 
e9d354ddc802: Already exists 
747b166626e9: Already exists 
2c0d1c428778: Already exists 
59f6c0bc2574: Pull complete 
30d3d7789dd4: Pull complete 
638c79a869c5: Pull complete 
2e5d8ad43464: Pull complete 
Digest: sha256:dedad8a636a6198b3efc6dcc5a65388d08f3c82bae9f55d1a88fef37328d8100
Status: Downloaded newer image for bardolf/mujrozhlas:latest
 ______  _     _   _____    ______   _____ _______ _     _ _                 _     ______ _______
|  ___ \| |   | | (_____)  (_____ \ / ___ (_______) |   | | |        /\     | |   / _____|_______)
| | _ | | |   | |    _      _____) ) |   | | __   | |__ | | |       /  \     \ \ | /        __
| || || | |   | |   | |    (_____ (| |   | |/ /   |  __)| | |      / /\ \     \ \| |       / /
| || || | |___| |___| |          | | |___| / /____| |   | | |_____| |__| |_____) ) \_____ / /____
|_||_||_|\______(____/           |_|\_____(_______)_|   |_|_______)______(______(_)______|_______)
                                                                                    D0WNL0AD3R 0.9
09:42:06.476 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - Starting MujRozhlasDownloaderApplication using Java 11.0.11 on c1beaf2d16c7 with PID 7 (/app/mujrozhlas-downloader-0.9-SNAPSHOT.jar started by app in /app)
09:42:06.478 [main] DEBUG c.s.m.MujRozhlasDownloaderApplication - Running with Spring Boot v2.5.5, Spring v5.3.10
09:42:06.478 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - No active profile set, falling back to default profiles: default
09:42:06.930 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - Started MujRozhlasDownloaderApplication in 0.721 seconds (JVM running for 1.101)
09:42:06.931 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading from https://www.mujrozhlas.cz/cetba-na-pokracovani/pavel-sanajev-pochovejte-me-pod-podlahu
Starting ChromeDriver 94.0.4606.61 ([1633945324187b.7381f95]8[3S8EeVdE0RbE1]c:6 9bbibn4de(5)1 efaa0i2l5e2d1:7 1C8a5n4n9o1t5 -arssigne frse/qbureasntcehd- haedaddrse/s4s6 0(69@9{)#
1204}) on port 32045
Only local connections are allowed.
Please see https://chromedriver.chromium.org/security-considerations for suggestions on keeping ChromeDriver safe.
ChromeDriver was started successfully.
09:42:07.614 [Forwarding newSession on session null to remote] INFO  o.o.s.remote.ProtocolHandshake - Detected dialect: W3C
09:42:13.545 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Pavel Sanajev: Pochovejte mě pod podlahu/_01_1. díl - https://portal.rozhlas.cz/sites/default/files/audios/e4e271425a0cb96143a3aa3fa074e7d7.mp3.
09:42:56.439 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Pavel Sanajev: Pochovejte mě pod podlahu/_02_2. díl - https://portal.rozhlas.cz/sites/default/files/audios/ff27fb7dd253e3f2802463501c3e259c.mp3.
09:43:23.241 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Pavel Sanajev: Pochovejte mě pod podlahu/_03_3. díl - https://portal.rozhlas.cz/sites/default/files/audios/9580f5edd9e0c3b9cfbd534113ec274d.mp3.
09:43:41.796 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Pavel Sanajev: Pochovejte mě pod podlahu/_04_4. díl - https://portal.rozhlas.cz/sites/default/files/audios/e0306fc09d2a6f19b0148efeaf5a6ee0.mp3.
09:43:54.604 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Pavel Sanajev: Pochovejte mě pod podlahu/_05_5. díl - https://portal.rozhlas.cz/sites/default/files/audios/f0249d3588c9ffac16ff522825180cb4.mp3.
09:44:05.887 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Pavel Sanajev: Pochovejte mě pod podlahu/_06_6. díl - https://portal.rozhlas.cz/sites/default/files/audios/590df50f8c42af1e88dcc61ce0c99d28.mp3.
09:44:19.060 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Pavel Sanajev: Pochovejte mě pod podlahu/_07_7. díl - https://portal.rozhlas.cz/sites/default/files/audios/071efda45c384c26d61e76b472ffbbca.mp3.

``` 