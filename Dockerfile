FROM gradle AS builder
RUN mkdir -p /build
COPY . /build
WORKDIR /build
RUN gradle clean build bootJar
ENTRYPOINT bash

################################################
FROM ubuntu

RUN apt update \
    && apt install -y unzip libxi6 libgconf-2-4 default-jre \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /tmp

COPY google-chrome-stable_current_amd64.deb .

RUN export DEBIAN_FRONTEND=noninteractive  \
    && apt update \
    && apt install -y wget  xorg xserver-xorg xvfb libx11-dev libxext-dev \
    && apt install -y ./google-chrome-stable_current_amd64.deb locales\
    && rm ./google-chrome-stable_current_amd64.deb \
    && rm -rf /var/lib/apt/lists/*

RUN wget https://chromedriver.storage.googleapis.com/98.0.4758.80/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip \
    && mv chromedriver /usr/bin/chromedriver \
    && rm chromedriver_linux64.zip

RUN export DEBIAN_FRONTEND=noninteractive  \
    && apt update \
    && apt install -y youtube-dl \
    && rm -rf /var/lib/apt/lists/*

# Set the locale
RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && locale-gen
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8


RUN mkdir -p /download

COPY --from=builder /build/build/libs/mujrozhlas-downloader-0.9-SNAPSHOT.jar /app/

RUN useradd -ms /bin/bash app
USER app

WORKDIR /app

ENTRYPOINT java $JAVA_OPTS -jar mujrozhlas-downloader-0.9-SNAPSHOT.jar --chrome.driver.binary.path=/usr/bin/chromedriver --chrome.headless=true --output.folder=/download $0 $@
