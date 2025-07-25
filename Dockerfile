# --- Build phase ---
FROM gradle:jdk23 AS builder

WORKDIR /build
COPY . .

RUN gradle clean build bootJar

# --- Runtime image ---
FROM ubuntu:25.04

ENV DEBIAN_FRONTEND=noninteractive

# Nainstaluj základní závislosti a JRE
RUN apt update
RUN apt install -y unzip libxi6 openjdk-24-jre wget xorg xvfb libx11-dev libxext-dev locales
RUN rm -rf /var/lib/apt/lists/*

# Nastav lokalizaci
RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && locale-gen
ENV LANG="en_US.UTF-8"
ENV LANGUAGE="en_US:en"
ENV LC_ALL="en_US.UTF-8"

# Instaluj Chrome
WORKDIR /tmp

COPY google-chrome-stable_current_amd64.deb .

RUN apt update && apt install -y ./google-chrome-stable_current_amd64.deb \
    && rm ./google-chrome-stable_current_amd64.deb \
    && rm -rf /var/lib/apt/lists/*

# Instaluj odpovídající chromedriver
RUN wget https://storage.googleapis.com/chrome-for-testing-public/138.0.7204.168/linux64/chromedriver-linux64.zip \
    && unzip chromedriver-linux64.zip \
    && mv chromedriver-linux64/chromedriver /usr/bin/chromedriver \
    && chmod +x /usr/bin/chromedriver \
    && rm -rf chromedriver-linux64*

RUN wget https://github.com/yt-dlp/yt-dlp/releases/download/2025.07.21/yt-dlp_linux \
    && chmod +x yt-dlp_linux \
    && mv yt-dlp_linux /usr/local/bin/yt-dlp_linux

RUN mkdir -p /download

COPY --from=builder /build/build/libs/mujrozhlas-downloader-0.10-SNAPSHOT.jar /app/

RUN useradd -ms /bin/bash app
USER app

WORKDIR /app

ENTRYPOINT ["java", "-jar", "mujrozhlas-downloader-0.10-SNAPSHOT.jar", "--chrome.driver.binary.path=/usr/bin/chromedriver", "--chrome.headless=true", "--output.folder=/download"]
