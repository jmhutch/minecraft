---
title: Creative Tabs 
permalink: /creativetabs/
order: 6
comments: true
disqus: creativetabs
---

`CreativeTabs` is a simple thing that lets you create your own tabs to put your mod's items for creative mode. Not much to explain here, so on with the show...

In the `Ref` class add the method:

```java
public static CreativeTabs tabExample = new CreativeTabs("tab_Example") {
  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(ModItems.OBSIDIAN_INGOT);
  }
};
```

This creates our static `tabExample` object that we can reference and pass around wherever we need it. We give it our Obsidien Ingot texture for its icon, but could use anything we have access to, including all the vanilla textures under `Items.*`.

For pretty stringificaiton, in `en_US.lang` add:

```
itemGroup.tab_Example=My Example Mod
```

Then to use our fancy new tab, in the `ModITems` class, change:

```java
public static Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot", CreativeTabs.BUILDING_BLOCKS);
``` 
to

```java
public static Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot", Ref.tabExample);
```

You can add as many new `CreativeTabs` objects as you like, and assign them different Icons, then pass them to different items as you create them. Most mods seem to stick to 1-3 tabs, with a few bigger ones weighing in at closer to 5, so it's really up to you, how much stuff you've got, and how it makes sense to partition it.

That's about it for that. Told you it was simple.
