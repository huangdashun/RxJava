#!/usr/bin/env bash

./gradlew clean
./gradlew assembleDebug

// fir.im 测试环境  账号需要申请
fir login 9a44776c3563fc133178ddaeb40ce030
fir publish app/build/outputs/apk/app-dev-release.apk