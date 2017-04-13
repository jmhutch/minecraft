---
title: Setup
permalink: /setup/
order: 2
comments: true
disqus: setup
---

### Java

Download/install Java JDK 8 (64 bit is preferable if your system supports it)

This series does not cover learning Java or installing the JDK. You should have the Java 8 JDK installed already.

### IDE

Download/install [Eclipse (Neon)](https://www.eclipse.org/) or [IntelliJ IDEA](https://www.jetbrains.com/idea/) if you don't already have one of them. Since you already know Java you should likely be familiar with one of these, or IDEs in general, that you can install one without detailed instructions.

You can probably use any decent IDE, particularly if it supports Gradle, but configuring it will be an excercise left to the user in this case. The above are auto-configurable through Forge and are the easiest way to get up and running.

### Forge

As this tutorial series is for Forge, you'll need to download the forge MDK package and extract it somewhere handy. This will be your project's working directory ongoing, so don't use a temp dir or whatnot, find it a nice home. 

On the [Forge Files site](http://files.minecraftforge.net/) you'll want to select your version (1.11 for this tutorial), click the 1.11.2 dropdown, then under "Download Recommended" select the disk icon labelled 'Mdk'. This should give you the archive you need.

You can safely delete most of the cruft, so long as you're left with:

* src/
* build.gradle
* gradle.properties
* gradle/
* gradlew
* gradlew.bat

### Gradle

Forge uses gradle to configure and build, so it's a good idea to look around the build.gradle file and set any obvious variables. For instance, you'll probably want:

```
version = "0.0.1"
group = "com.example.mod"
archivesBaseName = "mod"
```

filled in with your info, and set:

```
sourceCompatibility = JavaVersion.VERSION_1_8 targetCompatibility = JavaVersion.VERSION_1_8
```

so that you can target Java 1.8 and all it's fancy new goodies.

Finally, make sure you specify the correct minecraft/forge version - it should be correct by default, but in case you need to change it, the format is `version = "<minecraft version>-<forge version>"`. 

You may want to update the `mappings` to new snapshots periodically if you're running into lots of obfuscated method names, as this will continue to deobfuscate the code as they progress in such. Re-run `./gradlew setupDecompWorkspace` after updating your mappings. [MCPBot](http://export.mcpbot.bspk.rs/) is where to find the latest mappings.


```
minecraft {
    version = "1.11.2-13.20.0.2228"
    runDir = "run"
    mappings = "snapshot_20161220"
}
```

then run `./gradlew setupDecompWorkspace eclipse` or `./gradlew setupDecompWorkspace idea` depending on what IDE you selected.

:boom: If this fails with an out of memory or other odd looking error, you can attempt to adjust the amount of memory available to Java in the gradle process (gradle.properties - `org.gradle.jvmargs=-Xmx3G
`), or, in my experience, it could mean you're using a 32 bit Java when you should be using 64. This is configured in your system settings in windows, or by setting the correct JAVA_HOME and calling the correct java binary in linux. Again, you should be familiar with these concepts already. 

Some tutorials say to then set your workspace to the eclipse subdir that's created or import the .ipr file that's made for IDEA, but I find if you just set your workspace to the directory just below where you extracted the forge archive, or inside that dir, it'll pick up your project and all the grade build/run stuff perfectly. Doing anything beyond that did not work for me, so ymmv.

To setup under eclipse, set your workspace as described above, then go to `File` -> `Import...`, and select `Gradle`, `Gradle Project`, navigate to the directory you extracted the Forge MDK into, click Next, add your Java home directory if you're using a different jdk than default, any preferred JVM options. These options are for gradle, not your project build.

For IDEA, just open your project dir and it should "just work". :sparkles: 

Last, for either IDE, go into your run configuration settings and find the predefined Client option. You may want to add options to increase memory available to Java under the JVM options, and you'll very probably want to add, under Program arguments, `--username your@minecraftlogin.com --password yourmcpassword` - this way you won't use the random dev player and will login to your real Mojang account, which will allow you to run the dev server and connect to it with the dev client. Testing Client/Server and not just the Client is useful to make sure you haven't accidentally created any server-bombing code. :wink:






