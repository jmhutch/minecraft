---
title: Making Food
permalink: /food/
order: 6.5
comments: true
disqus: food
---

Under `com.example.mem.items` add the class `BaseFoodItem`.

```java
public class BaseFoodItem extends ItemFood {
  PotionEffect[] effects;

  public BaseFoodItem(int amount, boolean isWolfFood, PotionEffect... potienEffects) {
    super(amount, isWolfFood);
    this.effects = potienEffects;
  }

  public BaseFoodItem(int amount, Float saturation, boolean isWolfFood, PotionEffect... potionEffects) {
    super(amount, saturation, isWolfFood);
    this.effects = potionEffects;
  }

  @Override
  protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
    for (PotionEffect effect : effects) {
      player.addPotionEffect(effect);
    }
  }
}
```

This creates a nice base food class that has 2 constructors - one with and one without saturation arguments, to mirror our `super()` constructors - and adds an option to pass in any number of `PotionEffect` effects (including none) that will be applied upon eating said food.

Then, in the same package, create your class for your food item, like `ItemHealingHerb` and add:

```java
public class ItemHealingHerb extends BaseFoodItem {
  public ItemHealingHerb(String name, CreativeTabs tab) {
    super(5, 1.0F, true, new PotionEffect(Potion.getPotionById(21), 10, 100, true, true));
    
    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());    
    this.setCreativeTab(tab);
  }
```

This specifies all our attributes for our food item by passing them to the `super()` constructor, then we add the `Item` to the `ModItems` class like anything else. Note that we don't use the `BaseItem` to inherit from since we need to inherit from `ItemFood` instead of `Item` to get overrides like `onFoodEaten` and other foodie behavoirs.

In `ModItems` add:

```java
public static Item HEALING_HERB = new ItemHealingHerb("healing_herb", Ref.tabExample);

```

and update `RegisterItemHandler` to add the new `Item`:

```java
  public static Item[] items = {
    ModItems.OBSIDIAN_INGOT, ModItems.HEALING_HERB
  };
```

Which instantiates and registers our food.

Don't forget the `healing_herb.json` file:

```json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "mem:items/healing_herb"
  }
}
```

and a texture in `assets.textures.item`.

