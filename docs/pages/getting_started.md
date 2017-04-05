---
title: Getting Started
permalink: /getting_started/
order: 3
comments: true
disqus: gettingstarted
---

add package `com.example.mem`

add class `ExampleMod`

add class `Ref`

in `Ref` add:

```java
public class Ref {
  public static final String MODID = "mem";
  public static final String MOD_NAME = "My Example Mod";
  public static final String VERSION = "0.0.1";
}
```

in class `ExampleMod`

```java
@Mod(modid = Ref.MODID, version = Ref.VERSION, name = Ref.MOD_NAME)
public class ExampleMod
{
  @Instance(Ref.MODID)
  public static Mod INSTANCE;

  @SidedProxy(clientSide = "com.example.mem.proxy.ClientProxy", serverSide = "com.example.mem.proxy.CommonProxy")
  public static CommonProxy proxy;

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    proxy.preInit();
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    proxy.init();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    proxy.postInit();
  }
}
```

add package `com.example.mem.proxy`

add class `CommonProxy` to `proxy` package

in Common proxy:

```java
public class CommonProxy {

  public void preInit() { }

  public void init() { }

  public void postInit() { }
}
```

add class `ClientProxy` to `proxy` package

```java
public class ClientProxy extends CommonProxy {

  @Override
  public void preInit() {
    super.preInit();
  }

  @Override
  public void init() {
    super.init();
  }

  @Override
  public void postInit() {
    super.postInit();
  }
}
```

run client and make sure it starts without errors...

