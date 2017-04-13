---
title: Creating Items
permalink: /items/
order: 4
comments: true
disqus: items
---

Creating `Item`s is a good place to start out since it's both relatively simple and lets you get some tangible stuff into the game right off the bat. The process is basically creating an `Item`, registering it, then registering its renderer.

Most tutorials will have you create a class for each `Item` containing 3 methods to create, register, and render the item, then have you register the `init` and renderer methods in the proxy via your main class. However, the [Forge Documentation on registries](https://mcforge.readthedocs.io/en/latest/concepts/registries/) specifies that the preferred way of registering things is through `RegistryEvent` handlers, so that's how I'm going to do it. This has the effect of registering things _before_ `preIinit`.

First add a package `com.example.mem.item`.

Start with creating the class `ItemBase` that we'll use as our `Item` base class. Have it extend `net.minecraft.item.Item`.

```java
public class ItemBase extends Item {
  ItemBase(String name, CreativeTabs tab) {
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
  public static Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot", CreativeTabs.BUILDING_BLOCKS);
}
```

This is where we'll create and keep static references to all of our `Item`s. We'll start with a simple `Item` with no frills - an ingot. We'll make it an Obsidian ingot just for fun. So that you can find your `Item` we specify that it'll show up under the `CreativeTabs.BUILDING_BLOCKS` tab, which means in creative mode you can find it under the Blocks tab.


Now we need to create the class and methods to register our `Item`(s) and texture(s). Many people prefer to put this in a separate class, but I think it's nice to keep my `Item` related stuff together, so I create an inner class `RegisterItemHandler` inside my `ModItems` class. 

```java
public class ModItems {
  public static Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot", CreativeTabs.BUILDING_BLOCKS);

  @Mod.EventBusSubscriber
  public static class RegisterItemHandler {

    public static Item[] items = {
        ModItems.OBSIDIAN_INGOT
    };
    
    /*
     * Register ModItems
     */
      @SubscribeEvent
      public static void registerItems(RegistryEvent.Register<Item> event) {
        for(Item item : items) {
          event.getRegistry().register(item);
          }
      }

      /*
       * Register Textures
       */
      @SubscribeEvent
      @SideOnly(Side.CLIENT)
      public static void registerModels(ModelRegistryEvent event) {
        for(Item item : items ) {
          registerItem(item);
        }
      }
      
      public static void registerItem(Item item) {
          ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
      }
      
      public static void registerItem(Item item, int meta, String fileName) {
          ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(fileName, "inventory"));
      }
  }
}
```

This is where we'll register our `Item`s and their renderers, as mentioned previously, via `RegistryEvent`s.

The `@Mod.EventBusSubscriber` decorator denotes all `@SubscribeEvent` methods are automatically registered as if we'd registered them via the `MinecraftForge.EVENT_BUS.register` method in the @Mod class's constructor - but is a little more elegant about it. These events are fired before the `preInit()` `Event`. First `Register<Block>` fires, then `Items`, then everything else. Refer to the [Forge Documentation](https://mcforge.readthedocs.io/en/latest/concepts/registries/) for more details regarding registires (as this is one area the documentation is fairly extensive).

The `registerItems` method adds items to the item registry on the `Register<Item>` `Event`, and `registerModels` associates items with their texture path on the `ModelRegistryEvent` `Event`. We'll cover `Event`s more extensively [later on](/events/) but this is enough to get us started.

If you load the game now you should see your Obsidian Ingot `Item` in game, but it won't yet have a [texture](/textures/) - you'll just see a nice purple and black checkered blob instead. It's a good start.

Also, you may notice your item has a very non-friendly name, item.obsidian_ingot.name. We can fix that by going into src/main/resources and creating the package `assets.mem.lang` and then creating a plaintext file called `en_US.lang`. In this file add the line:

```
item.obsidian_ingot.name=Obsidian Ingot
```

Now your item should have a proper name in game.

