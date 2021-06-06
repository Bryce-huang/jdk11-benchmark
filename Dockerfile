FROM ccr.ccs.tencentyun.com/konajdk_repo/tencent_konajdk_11
ADD /target/benchmarks.jar /benchmarks.jar
WORKDIR  /
CMD  java -jar benchmarks.jar -gc true -rf json  -rff  /usr/log/konajdk.json -jvmArgs "-XX:InitialRAMPercentage=80 -XX:MaxRAMPercentage=80 -XX:+UseG1GC -Xcomp"
