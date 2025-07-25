# mujRozhlas.cz downloader


![????](doc/img/01_audio_unavailable.png)

Autorská práva dovolující ponechat zajímavé a skvělé počiny __pouze 1 měsíc__ - obrovská škoda. Zazálohujte si pro vlastní potřebu audio soubory.

Utility downloads either whole series, if it is one or just one file otherwise. 

## Run via Docker


```shell
docker run --rm --user $(id -u):$(id -g) -v `pwd`:/download bardolf/mujrozhlas --url.to.download='https://www.mujrozhlas.cz/cteni-na-pokracovani/prasina-1-napinave-patrani-po-davnem-tajemstvi-v-zahadne-prazske-ctvrti'
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
$ docker run --rm --user $(id -u):$(id -g) -v `pwd`:/download bardolf/mujrozhlas --url.to.download='https://www.mujrozhlas.cz/cteni-na-pokracovani/prasina-1-napinave-patrani-po-davnem-tajemstvi-v-zahadne-prazske-ctvrti'
 ______  _     _   _____    ______   _____ _______ _     _ _                 _     ______ _______
|  ___ \| |   | | (_____)  (_____ \ / ___ (_______) |   | | |        /\     | |   / _____|_______)
| | _ | | |   | |    _      _____) ) |   | | __   | |__ | | |       /  \     \ \ | /        __
| || || | |   | |   | |    (_____ (| |   | |/ /   |  __)| | |      / /\ \     \ \| |       / /
| || || | |___| |___| |          | | |___| / /____| |   | | |_____| |__| |_____) ) \_____ / /____
|_||_||_|\______(____/           |_|\_____(_______)_|   |_|_______)______(______(_)______|_______)
                                                                                  D0WNL0AD3R 0.10.1
19:26:43.602 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - Starting MujRozhlasDownloaderApplication v0.10-SNAPSHOT using Java 24.0.2 with PID 1 (/app/mujrozhlas-downloader-0.10-SNAPSHOT.jar started by ubuntu in /app)
19:26:43.603 [main] DEBUG c.s.m.MujRozhlasDownloaderApplication - Running with Spring Boot v3.5.4, Spring v6.2.9
19:26:43.604 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - No active profile set, falling back to 1 default profile: "default"
19:26:43.983 [main] INFO  c.s.m.MujRozhlasDownloaderApplication - Started MujRozhlasDownloaderApplication in 0.579 seconds (process running for 0.958)
19:26:43.986 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading from https://www.mujrozhlas.cz/cteni-na-pokracovani/prasina-1-napinave-patrani-po-davnem-tajemstvi-v-zahadne-prazske-ctvrti
19:26:51.554 [main] INFO  com.skybit.mujrozhlas.Downloader - It is a series (jedná se o seriál).
19:26:58.020 [main] INFO  com.skybit.mujrozhlas.Downloader - MPD stream detected.
19:26:58.028 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading 01_Prašina/1. díl - yt-dlp_linux https://croaod.cz/stream/5c666f13-eee2-4527-aa42-81c3981abee5.m4a/manifest.mpd --output /download/01_Prasina_1._dil.m4a.
[generic] Extracting URL: https://croaod.cz/stream/5c666f13-eee2-4527-aa42-81c3981abee5.m4a/manifest.mpd
[generic] manifest: Downloading webpage
WARNING: [generic] Falling back on generic information extractor
[generic] manifest: Extracting information
[info] manifest: Downloading 1 format(s): p0aa0br193445
[dashsegments] Total fragments: 77
[download] Destination: /download/01_Prasina_1._dil.m4a
[download] 100% of   17.77MiB in 00:00:08 at 2.04MiB/s                  
WARNING: manifest: writing DASH m4a. Only some players support this container. Install ffmpeg to fix this automatically
WARNING: manifest: Possible duplicate MOOV atoms. Install ffmpeg to fix this automatically
19:27:08.512 [main] INFO  com.skybit.mujrozhlas.Downloader - MPD stream detected.
19:27:08.512 [main] INFO  com.skybit.mujrozhlas.Downloader - Downloading 02_Prašina/2. díl - yt-dlp_linux https://croaod.cz/stream/a3f85009-b4ec-4caf-9b9a-c92ce3cb177b.m4a/manifest.mpd --output /download/02_Prasina_2._dil.m4a.
[generic] Extracting URL: https://croaod.cz/stream/a3f85009-b4ec-4caf-9b9a-c92ce3cb177b.m4a/manifest.mpd
[generic] manifest: Downloading webpage
WARNING: [generic] Falling back on generic information extractor
[generic] manifest: Extracting information
[info] manifest: Downloading 1 format(s): p0aa0br193598
[dashsegments] Total fragments: 78
[download] Destination: /download/02_Prasina_2._dil.m4a
``` 

## Build & Publish

```shell
docker build -t bardolf/mujrozhlas .
docker tag bardolf/mujrozhlas bardolf/mujrozhlas:<version>
docker push  bardolf/mujrozhlas:<version>
docker push  bardolf/mujrozhlas:latest
```