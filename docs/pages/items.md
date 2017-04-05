---
title: Creating Items
permalink: /items/
order: 4
comments: true
disqus: items
---

Creating `Item`s is a good place to start out since it's both relatively simple and lets you get some tangible stuff into the game right off the bat. The process is basically creating an `Item`, registering it, then registering its renderer.

Most tutorials will have you create a class for each `Item` containing 3 methods to create, register, and render the item, then have you register the `init` and renderer methods in the proxy via your main class. However, the [Forge Documentation on registries](https://mcforge.readthedocs.io/en/latest/concepts/registries/) specifies that the preferred way of registering things is through `RegistryEvent` handlers, so that's how I'm going to do it. This has the effect of registering things _before_ `preIinit`.

First add a package `com.example.mem.items`.

Start with creating the class `BaseItem` that we'll use as our `Item` base class.

```java
public class BaseItem extends Item {
  BaseItem(String name, CreativeTabs tab) {
    super();
    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());
    this.setCreativeTab(tab);
  }
}
```

This sets basic info for your `Item` during creation. Multiple constructors could be added to pass in additional options for special `Item`s. We'll extend this later to create `Item`s that require more than these basic options.

Now create the class `ModItems`:

```java
public class ModItems {
  public static Item OBSIDIAN_INGOT = new BaseItem("obsidian_ingot", CreativeTabs.BUILDING_BLOCKS);
}
```

This is where we'll create and keep static references to all of our `Item`s. We'll start with a simple `Item` with no frills - an ingot. We'll make it an Obsidian ingot just for fun. So that you can find your `Item` we specify that it'll show up under the `CreativeTabs.BUILDING_BLOCKS` tab, which means in creative mode you can find it under the Blocks tab.

Create the package `com.example.mem.handlers`, and in it, the class `RegisterItemHandler`

```java
@Mod.EventBusSubscriber
public class RegisterItemHandler {
  public static Item[] items = {
    ModItems.OBSIDIAN_INGOT
  };

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    for(Item item : items) {
      event.getRegistry().register(item);
    }
  }

  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public static void registerModels(ModelRegistryEvent event) {
    for(Item item : items ) {
      ModelLoader.setCustomModelResourceLocation(item, 0, 
          new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }
  }
}
```

This is where we'll register our `Item`s and their renderers, as mentioned previously, via `RegistryEvent`s.

The `@Mod.EventBusSubscriber` decorator denotes all `@SubscribeEvent` methods are automatically registered as if we'd registered them via the `MinecraftForge.EVENT_BUS.register` method in the @Mod class's constructor - but is a little more elegant about it. These events are fired before the `preInit()` `Event`. First `Register<Block>` fires, then `Items`, then everything else. Refer to the [Forge Documentation](https://mcforge.readthedocs.io/en/latest/concepts/registries/) for more details regarding registires (as this is one area the documentation is fairly extensive).

The `registerItems` method adds items to the item registry on the `Register<Item>` `Event`, and `registerModels` associates items with their texture path on the `ModelRegistryEvent` `Event`. We'll cover `Event`s more extensively [later on](/events/) but this is enough to get us started.

If you load the game now you should see your Obsidian Ingot `Item` in game, but it won't yet have a [texture](/textures/) - you'll just see a nice purple and black checkered blob instead. It's a good start.

