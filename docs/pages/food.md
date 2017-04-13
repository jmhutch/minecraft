---
title: Making Food
permalink: /food/
order: 6.5
comments: true
disqus: food
---

Creating a `ItemFood` is a lot like creating a normal `Item`, except there are different Overrides and such and we extend `ItemFood` instead of `Item`. Otherwise we still have to create, register, and register a renderer as usual.

Under `com.example.mem.items` add the class `ItemFoodBase` that extends `ItemFood`.

```java
public class ItemFoodBase extends ItemFood {
  PotionEffect[] effects;

  public ItemFoodBase(int amount, boolean isWolfFood, PotionEffect... potienEffects) {
    super(amount, isWolfFood);
    this.effects = potienEffects;
  }

  public ItemFoodBase(int amount, Float saturation, boolean isWolfFood, PotionEffect... potionEffects) {
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

You could, of course, forego this class altogether and just write each food `Item` separately, extending `ItemFood` directly in each. This may be a better option if you have few foods that have a lot of functionality. Abstracting the base class out is a better option if you plan to have several foods that are accomodatable with a few constructor overloads.

Then, in the same package, create your class for your food item, like `ItemHealingHerb` that extends `ItemFoodBase` and add:

```java
public class ItemHealingHerb extends ItemFoodBase {
  public ItemHealingHerb(String name, CreativeTabs tab) {
    super(5, 1.0F, true, new PotionEffect(MobEffects.REGENERATION, 400, 1));
    
    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());    
    this.setCreativeTab(tab);
  }
}
```

This specifies all our attributes for our food item by passing them to the `super()` constructor, then we add the `Item` to the `ModItems` class like anything else. Note that we don't use the `BaseItem` to inherit from since we need to inherit from `ItemFood` instead of `Item` to get overrides like `onFoodEaten` and other foodie behavoirs.

Note that although there are 10 hunger "bars" in the UI, there are actually 20 points. Using this example we feed only 1/4 of the player's hunger with 5 points.

In `ModItems` add:

```java
public static Item HEALING_HERB = new ItemHealingHerb("healing_herb", Ref.tabExample);

```
to create the object instance and update `RegisterItemHandler` to add the new `Item`:

```java
  public static Item[] items = {
    ModItems.OBSIDIAN_INGOT, ModItems.HEALING_HERB
  };
```

Which instantiates and registers our food. Since `ItemFood` extends `Item` we can squish it into the Item[] array without a problem.

Don't forget the `healing_herb.json` file:

```json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "mem:items/healing_herb"
  }
}
```

and a texture in `assets.mem.textures.item`.

Now pop into the game, /give @p mem:healing_herb, give yourself /effect @p hunger 10 100 and get nice and hungry and eat some of your food! :herb:

