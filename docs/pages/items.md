---
title: Creating Items
permalink: /items/
order: 4
comments: true
disqus: items
---

add package `com.example.mem.items`

create class `BaseItem`

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

This sets basic info for your item during creation. Multiple constructors could be added to pass in additional options for special items. We'll subclass this later to create items that require more than these basic options.

create class `ModItems`

```java
public class ModItems {
  public static Item OBSIDIAN_INGOT = new BaseItem("obsidian_ingot", CreativeTabs.BUILDING_BLOCKS);
}
```

This is where we'll create and keep reference to all of our items. We'll start with a simple item with no frills - an ingot. We'll make it an Obsidian ingot since one doesn't exist.

create package `com.example.mem.handlers`

create class `RegisterItemHandler`

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

The `@Mod.EventBusSubscriber` decorator denotes all `@SubscribeEvent` methods are automatically registered as if we'd registered them on the EVENT_BUS at the end of the main class's constructor. These events are fired before the `preInit()` Event. First `Register<Block>` fires, then `Items`, then everything else,


The `registerItems` method adds items to the item registry on the `Register<Item>` Event, and `registerModels` associates items with their texture path.


