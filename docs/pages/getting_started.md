---
title: Getting Started
permalink: /getting_started/
order: 3
comments: true
disqus: gettingstarted
---

Every Minecraft mod starts with some very similar boilerplate. So much so, that I question why the setupDecompWorkspace doesn't just create this handful of starter files for us, but it's easy enough to put together anyway.

First we'll want to create a package. I'm calling this "My Example Mod" and using the `com.example` namespace. Typically, mods will use either the usual `com.domain` namespace, or, lacking a dedicated domain, `userhandle.modname` is frequently used. In addition, a (frequently 3 letter) mod ID is typically attached, so for 'My Example Mod' I'll use `com.example.mem` as my base package. If I were actually writing a mod for myself, I might use `jmhutch.MyMod.mem` or `jmhutch.mem`, for instance, but this is all really just a matter of preference so long as your namespace is unique enough that it doesn't conflict with Minecraft, Forge, or any other modules that are loaded alongside yours.

That said, let's start by adding the package `com.example.mem`

Then we'll need a main class, typically named after the mod, or `Main`, though I prefer the former, create the class `ExampleMod`.

Before we fill it in, also create a class called `Ref` or `Reference`; this is where we'll keep a number of static constants that can be easily referenced throughout the mod.

In class `Ref` add some basic constants:

```java
public class Ref {
  public static final String MODID = "mem";
  public static final String MOD_NAME = "My Example Mod";
  public static final String VERSION = "0.0.1";
}
```

MODID can be whatever you want, but is typically short (usually 3 letters) and is generally the same as you selected for your mod namespace.

Back in class `ExampleMod`, we can now add the basic boilerplate:

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

Every mod starts with the @Mod decorator followed by at least `modid` and `version` params. If you don't add `name` you'll get a slight complaint when firing up the client, but it'll just pick one for you and move on.

We then create an instance of our class.

The @SidedProxy decorator is a key point and easily the most complicated part of the intial setup. Basically, it specifies two classes - client and common/server - and allows you to call one instance that will invoke the client class for client operations and a 'common' class for server-side or common operations. We want to create the `proxy` package, then a `CommonProxy` class, then create a `ClientProxy` class that extends it. This way we can Override all the common functions with client-specific code in the `ClientProxy` so that it gets called as needed.

The best explanation I've seen of 'sidedness' is from the [Forge Documentation on Sides](https://mcforge.readthedocs.io/en/latest/concepts/sides/).

Go ahead and add package `com.example.mem.proxy`

Then add the class `CommonProxy` to `proxy` package as:

```java
public class CommonProxy {

  public void preInit() { }

  public void init() { }

  public void postInit() { }
}
```

Add class `ClientProxy` containing:

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

It should make some sense now how these 3 classes interact. Each defines `preInit`, `init`, and `postInit`, and the base class calls the proxy which evokes either the Common or Client proxy class depending on which thread it's in. We'll fill these in appropriately as we go.

For now, go ahead and run the client task in eclipse and make sure it fires up without error before proceeding.


