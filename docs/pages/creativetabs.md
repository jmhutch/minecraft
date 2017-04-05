---
title: Creative Tabs 
permalink: /creativetabs/
order: 6
comments: true
disqus: creativetabs
---

In the `Ref` class add the method:

```java
public static CreativeTabs tabExample = new CreativeTabs("tab_Example") {
  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(ModItems.OBSIDIAN_INGOT);
  }
};
```

in `en_US.lang` add:

```
itemGroup.tab_Example=My Example Mod
```

In the `ModITems` class, change:

```java
public static Item OBSIDIAN_INGOT = new BaseItem("obsidian_ingot", CreativeTabs.BUILDING_BLOCKS);
``` 

```java
public static Item OBSIDIAN_INGOT = new BaseItem("obsidian_ingot", Ref.tabExample);
```

You can add as many new `CreativeTabs` objects as you like, and assign them different Icons, then pass them to different items as you create them. Most mods seem to stick to 1-3 tabs, with a few bigger ones weighing in at closer to 5, so it's really up to you, how much stuff you've got, and how it makes sense to partition it.

