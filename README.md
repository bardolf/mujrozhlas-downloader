# mujRozhlas.cz downloader


![????](doc/img/01_audio_unavailable.png)

Autorská práva dovolující ponechat zajímavé a skvělé počiny pouze 1 měsíc - obrovská škoda.

```shell
./gradlew bootRun --args='https://www.mujrozhlas.cz/cetba-s-hvezdickou/za-hranou-lasky-lolita-vladimira-nabokova-jako-cetba-s-hvezdickou'  
```

```log
> Task :bootRun
 ______  _     _   _____    ______   _____ _______ _     _ _                 _     ______ _______
|  ___ \| |   | | (_____)  (_____ \ / ___ (_______) |   | | |        /\     | |   / _____|_______)
| | _ | | |   | |    _      _____) ) |   | | __   | |__ | | |       /  \     \ \ | /        __
| || || | |   | |   | |    (_____ (| |   | |/ /   |  __)| | |      / /\ \     \ \| |       / /
| || || | |___| |___| |          | | |___| / /____| |   | | |_____| |__| |_____) ) \_____ / /____
|_||_||_|\______(____/           |_|\_____(_______)_|   |_|_______)______(______(_)______|_______)
                                                                                    D0WNL0AD3R 0.9
21:15:34.253 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - Starting MujRozhlasDownloaderApplication using Java 1.8.0_201 on desktop with PID 10149 (/home/milan/projects/mujrozhlas-downloader/build/classes/java/main started by milan in /home/milan/projects/mujrozhlas-downloader)
21:15:34.254 [main] DEBUG c.s.m.MujRozhlasDownloaderApplication - Running with Spring Boot v2.5.5, Spring v5.3.10
21:15:34.255 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - No active profile set, falling back to default profiles: default
Starting ChromeDriver 94.0.4606.61 (418b78f5838ed0b1c69bb4e51ea0252171854915-refs/branch-heads/4606@{#1204}) on port 31611
Only local connections are allowed.
Please see https://chromedriver.chromium.org/security-considerations for suggestions on keeping ChromeDriver safe.
ChromeDriver was started successfully.
21:15:35.186 [Forwarding newSession on session null to remote] INFO  o.o.s.remote.ProtocolHandshake - Detected dialect: W3C
21:15:35.232 [main] INFO  o.o.s.devtools.CdpVersionFinder - Found exact CDP implementation for version 94
21:15:35.331 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - Started MujRozhlasDownloaderApplication in 1.287 seconds (JVM running for 1.565)
21:15:35.332 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading from https://www.mujrozhlas.cz/cetba-s-hvezdickou/za-hranou-lasky-lolita-vladimira-nabokova-jako-cetba-s-hvezdickou
21:15:43.351 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_01_1. díl - https://portal.rozhlas.cz/sites/default/files/audios/07b96edc80a7248716d63803aa34b597.mp3.
21:15:47.729 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_02_2. díl - https://portal.rozhlas.cz/sites/default/files/audios/381104e27982ae79f2cefacb33276de5.mp3.
21:15:51.413 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_03_3. díl - https://portal.rozhlas.cz/sites/default/files/audios/15c363975d76e46ad3ec3506f1046732.mp3.
21:15:55.440 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_04_4. díl - https://portal.rozhlas.cz/sites/default/files/audios/919638d94c686b149d6240c946951fa7.mp3.
21:15:59.749 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_05_5. díl - https://portal.rozhlas.cz/sites/default/files/audios/7890297fba8891bca04860c3476186b2.mp3.
21:16:03.943 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_06_6. díl - https://portal.rozhlas.cz/sites/default/files/audios/51bdb1d67cc5c114a6bd596dee41d051.mp3.
21:16:09.346 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_07_7. díl - https://portal.rozhlas.cz/sites/default/files/audios/b1f97d8c4534b93c09f0705988ae77c9.mp3.
21:16:12.903 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_08_8. díl - https://portal.rozhlas.cz/sites/default/files/audios/7c43d218c18c09d05c4ec87b0ab8c837.mp3.
21:16:17.631 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_09_9. díl - https://portal.rozhlas.cz/sites/default/files/audios/399306e51ddf8dbca21e6989db0b8e28.mp3.
21:16:21.750 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_10_10. díl - https://portal.rozhlas.cz/sites/default/files/audios/17e209aa130a2c8507f2c3c898f1ed35.mp3.
21:16:25.421 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_11_11. díl - https://portal.rozhlas.cz/sites/default/files/audios/3be53039525822c46d5ad73ed3988240.mp3.
21:16:29.681 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_12_12. díl - https://portal.rozhlas.cz/sites/default/files/audios/48685560528f0f354808b170d66ba44c.mp3.
21:16:33.912 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_13_13. díl - https://portal.rozhlas.cz/sites/default/files/audios/27c83b25e256b1d0699bd2ff74b954e8.mp3.
21:16:38.674 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_14_14. díl - https://portal.rozhlas.cz/sites/default/files/audios/37aefb75b7eb8c99447d8f86e49dda4a.mp3.
21:16:42.879 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_15_15. díl - https://portal.rozhlas.cz/sites/default/files/audios/953d08943a81b649f31d6481c4e38934.mp3.
21:16:47.356 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_16_16. díl - https://portal.rozhlas.cz/sites/default/files/audios/632a3662f620fd8da6009fb658ff4c95.mp3.
21:16:51.509 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_17_17. díl - https://portal.rozhlas.cz/sites/default/files/audios/7a0396f56ae82c265e76bd8af80b1abc.mp3.
21:16:55.518 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_18_18. díl - https://portal.rozhlas.cz/sites/default/files/audios/9af94a33bb8cd721cbb3ebc6642c74f3.mp3.
21:16:59.555 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_19_19. díl - https://portal.rozhlas.cz/sites/default/files/audios/68557235bbd0ec7d320ce6eba53fda47.mp3.
21:17:04.017 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_20_20. díl - https://portal.rozhlas.cz/sites/default/files/audios/580baf588ecfc8b2e24b230a628b76e7.mp3.
21:17:08.609 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_21_21. díl - https://portal.rozhlas.cz/sites/default/files/audios/d491868ffe77fd2c850261af876324af.mp3.
21:17:12.478 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading Vladimir Nabokov: Lolita/_22_22. díl - https://portal.rozhlas.cz/sites/default/files/audios/cc5544aad2a239a0c45986d255b44f71.mp3.

``` 