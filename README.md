# aLIEz

English

Use reflection to dynamically load JDK dependencies, and do not directly use transformers to modify bytecode. Now it is directly dumped and researchers are required to analyze it themselves.

Kill the memory of various JavaEE containers and middleware

Usage: java -jar aLIEz.jar JVMID aLIEz-agent-1.0-SNAPSHOT.jar

Usage
````
➜ java -jar aLIEz.jar
usage: java -jar clearMemShell.jar <JVMID> "AgentLocate"
32866: org.jetbrains.idea.maven.server.RemoteMavenServer36
43237: org.jetbrains.jps.cmdline.Launcher /Applications/IntelliJ IDEA.app/Contents/lib/netty-common-4.1.52.Final.jar:/Applications/IntelliJ IDEA.app/Contents/lib/netty-resolver-4.1.52.Final.jar:/Applications/IntelliJ IDEA.app/Contents/plugins/java/lib/javac2.jar:/Applications/IntelliJ IDEA.app/Contents/lib/httpclient-4.5.12.jar:/Applications/IntelliJ IDEA.app/Contents/lib/plexus-component-annotations-1.7.1.jar:/Applications/IntelliJ IDEA.app/Contents/lib/maven-resolver-spi-1.3.3.jar:/Applications/IntelliJ IDEA.app/Contents/lib/util.jar:/Applications/IntelliJ IDEA.app/Contents/lib/platform-api.jar:/Applications/IntelliJ IDEA.app/Contents/lib/qdox-2.0-M10.jar:/Applications/IntelliJ IDEA.app/Contents/lib/asm-all-9.0.jar:/Applications/IntelliJ IDEA.app/Contents/lib/commons-lang3-3.10.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jna.jar:/Applications/IntelliJ IDEA.app/Contents/lib/trove4j.jar:/Applications/IntelliJ IDEA.app/Contents/lib/nanoxml-2.2.3.jar:/Applications/IntelliJ IDEA.app/Contents/lib/maven-resolver-api
45253: org.jetbrains.idea.maven.server.RemoteMavenServer36
69401: org.jetbrains.idea.maven.server.RemoteMavenServer36
51529: Behinder_v3.0_Beta6_mac.jar
56185: org.apache.catalina.startup.Bootstrap start
57449: aLIEz.jar
37197:
64783: org.jetbrains.idea.maven.server.RemoteMavenServer36
56879: Godzilla-V2.96.jar

15:18:23 in out/artifacts/aLIEz_jar
➜ java -jar aLIEz.jar 56185 "/Users/r00t4dm/Downloads/JavaAgent_CleanMemShell/target/aLIEz-agent-1.0-SNAPSHOT.jar"
[+]OK.i find a jvm.org.apache.catalina.startup.Bootstrap start
[+]agent is injected.
````

中文版本

采用反射动态加载JDK依赖，不会直接transformer去改bytecode 现在是直接dump出来依赖研究人员自己分析

杀各个JavaEE容器、中间件的内存马

使用方法：java -jar aLIEz.jar JVMID aLIEz-agent-1.0-SNAPSHOT.jar

![img.png](img.png)


使用方法
````
➜ java -jar aLIEz.jar
usage: java -jar clearMemShell.jar <JVMID> "AgentLocate"
32866: org.jetbrains.idea.maven.server.RemoteMavenServer36
43237: org.jetbrains.jps.cmdline.Launcher /Applications/IntelliJ IDEA.app/Contents/lib/netty-common-4.1.52.Final.jar:/Applications/IntelliJ IDEA.app/Contents/lib/netty-resolver-4.1.52.Final.jar:/Applications/IntelliJ IDEA.app/Contents/plugins/java/lib/javac2.jar:/Applications/IntelliJ IDEA.app/Contents/lib/httpclient-4.5.12.jar:/Applications/IntelliJ IDEA.app/Contents/lib/plexus-component-annotations-1.7.1.jar:/Applications/IntelliJ IDEA.app/Contents/lib/maven-resolver-spi-1.3.3.jar:/Applications/IntelliJ IDEA.app/Contents/lib/util.jar:/Applications/IntelliJ IDEA.app/Contents/lib/platform-api.jar:/Applications/IntelliJ IDEA.app/Contents/lib/qdox-2.0-M10.jar:/Applications/IntelliJ IDEA.app/Contents/lib/asm-all-9.0.jar:/Applications/IntelliJ IDEA.app/Contents/lib/commons-lang3-3.10.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jna.jar:/Applications/IntelliJ IDEA.app/Contents/lib/trove4j.jar:/Applications/IntelliJ IDEA.app/Contents/lib/nanoxml-2.2.3.jar:/Applications/IntelliJ IDEA.app/Contents/lib/maven-resolver-api
45253: org.jetbrains.idea.maven.server.RemoteMavenServer36
69401: org.jetbrains.idea.maven.server.RemoteMavenServer36
51529: Behinder_v3.0_Beta6_mac.jar
56185: org.apache.catalina.startup.Bootstrap start
57449: aLIEz.jar
37197:
64783: org.jetbrains.idea.maven.server.RemoteMavenServer36
56879: Godzilla-V2.96.jar

15:18:23 in out/artifacts/aLIEz_jar
➜ java -jar aLIEz.jar 56185 "/Users/r00t4dm/Downloads/JavaAgent_CleanMemShell/target/aLIEz-agent-1.0-SNAPSHOT.jar"
[+]OK.i find a jvm.org.apache.catalina.startup.Bootstrap start
[+]agent is injected.
