FROM gradle AS build
COPY . /app
WORKDIR /app
RUN gradle bootJar


################################################
FROM ubuntu

RUN apt update \
    && apt install -y unzip libxi6 libgconf-2-4 default-jre \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /tmp

RUN apt update \
    && apt install -y wget \
       xorg xserver-xorg xvfb libx11-dev libxext-dev \
    && wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && apt install -y ./google-chrome-stable_current_amd64.deb \
    && rm ./google-chrome-stable_current_amd64.deb \
    && rm -rf /var/lib/apt/lists/*

RUN wget https://chromedriver.storage.googleapis.com/94.0.4606.61/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip \
    && mv chromedriver /usr/bin/chromedriver \
    && rm chromedriver_linux64.zip

COPY --from=build /app/build/libs/mujrozhlas-0.9-SNAPSHOT.jar /app/

RUN useradd -ms /bin/bash app
USER app

WORKDIR /app

ENTRYPOINT java -jar mujrozhlas-0.9-SNAPSHOT.jar --chrome.driver.binary.path=/usr/bin/chromedriver
