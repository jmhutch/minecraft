---
title: Recipes
permalink: /recipes/
order: 6.5
comments: true
disqus: recipes
---

To illustrate recipes we're going to take our Obsidian Ingot `Item`, add a few classes for Armor and Tool creation, then create recipes to turn Ingots into useful Stuff. This will also illustrate how simple it is to add new `Item`s now that your foundation classes are set up.

Armor is the easiest, since we only need to extend `ItemArmor` for all 4 pieces. So in `com.example.mem.items` create the class `ItemModArmor` that extends `ItemArmor` with:

```java
public class ItemModArmor extends ItemArmor {
  public ItemModArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name, CreativeTabs tab) {
    super(materialIn, renderIndexIn, equipmentSlotIn);

    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());
    this.setCreativeTab(tab);
    
    this.setMaxStackSize(1);
  }

}
```

This should look mostly familiar with the addition of a few arguments passed to the constructor that are Armor specific, and we `setMaxStackSize()` to 1 since armor doesn't stack. The biggest difference is that we're passing in the `ArmorMaterial materialIn` param, which we'll create in the `ModItems` class so we can share it between multiple items, and the `EntityEquipmentSlot` argument, which specifies which piece we want.

In the `ModItems` class we'll first create a custom `ArmorMaterial`:

```java
  public static final ArmorMaterial obsidianArmorMaterial = EnumHelper
      .addArmorMaterial("obsidian", Ref.MODID + ":obsidian", 50, new int[] { 10, 10, 10, 10 }, 50, 
          SoundEvents.ITEM_ARMOR_EQUIP_IRON, 5.0F);
```

The params for `addArmorMaterial` are:

```java
EnumHelper.addArmorMaterial(String name, String textureName, int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness)
```

The `int[] reductionAmounts` is an array with damage reduction for each slot.

Place the above near the top of `ModItems` and then add:

```java
public static ItemModArmor OBSIDIAN_HELM = 
    new ItemModArmor(obsidianArmorMaterial, 1, EntityEquipmentSlot.HEAD, "obsidian_helm", Ref.tabExample);
public static ItemModArmor OBSIDIAN_CHEST = 
    new ItemModArmor(obsidianArmorMaterial, 1, EntityEquipmentSlot.CHEST, "obsidian_chest", Ref.tabExample);
public static ItemModArmor OBSIDIAN_LEGS = 
    new ItemModArmor(obsidianArmorMaterial, 2, EntityEquipmentSlot.LEGS, "obsidian_legs", Ref.tabExample);
public static ItemModArmor OBSIDIAN_FEET = 
    new ItemModArmor(obsidianArmorMaterial, 1, EntityEquipmentSlot.FEET, "obsidian_feet", Ref.tabExample);
```

Note the second param, `renderIndexIn` is set to 2 for leggings, that's because their texture is on a separate layer than the rest of the pieces. Refer to the [Textures and json](/textures/) article for more information.

Next the tools. Each inherits from its own subclass, so each will want its own class. You could be clever and inherit from the base class `ItemTool` (and may want to in some cases when customizing a tool heavily) and create a generic tool class, but creating separate classes gives us basic tool functionality for each type for free, and our classes can be reused indefinitely for different material types, so it's a reasonable investment.

You'll want to create, in `mod.example.mem.items.tools`, classes `ItemModAxe`, `ItemModHoe`, `ItemModPickAxe`, `ItemModSpade` and `ItemModSword`. Each will contain nearly the same thing:

```java
public class ItemModAxe extends ItemAxe {
  public ItemModAxe(ToolMaterial material, float dmg, float speed, String name, CreativeTabs tab) {
        super(material, dmg, speed);
        
    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());
    this.setCreativeTab(tab);
    
    this.setMaxStackSize(1);
  }
}
```

```java
public class ItemModHoe extends ItemHoe {
  public ItemModHoe(ToolMaterial material, String name, CreativeTabs tab) {
    super(material);

    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());
    this.setCreativeTab(tab);
    
    this.setMaxStackSize(1);
  }
}
```

```java
public class ItemModPickaxe extends ItemPickaxe {
  public ItemModPickaxe(ToolMaterial material, String name, CreativeTabs tab) {
    super(material);

    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());
    this.setCreativeTab(tab);
    
    this.setMaxStackSize(1);
  }
}
```

```java
public class ItemModSpade extends ItemSpade {
  public ItemModSpade(ToolMaterial material, String name, CreativeTabs tab) {
    super(material);

    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());
    this.setCreativeTab(tab);
    
    this.setMaxStackSize(1);
  }
}
```

```java
public class ItemModSword extends ItemSword {
  public ItemModSword(ToolMaterial material, String name, CreativeTabs tab) {
    super(material);

    this.setRegistryName(name);
    this.setUnlocalizedName(this.getRegistryName().getResourcePath());
    this.setCreativeTab(tab);
    
    this.setMaxStackSize(1);
  }
}
```

You'll notice `ItemAxe` takes slightly different params than the rest. That's because of a forge bug where you get an index out of bounds error if you call it normally. By using a different version of the constructor and passing in those args we get around it.

Now we can go back to `ModItems` and add a `ToolMaterial` and our tools:

```java
  public static final ToolMaterial obsidianMaterial = EnumHelper.addToolMaterial(Ref.MODID + ":obsidian", 0, 1000, 1.0F, 5.0F, 100);

  public static Item OBSIDIAN_PICKAXE = new ItemModPickaxe(obsidianMaterial, "obsidian_pickaxe", Ref.tabExample);
  public static Item OBSIDIAN_AXE = new ItemModAxe(obsidianMaterial, 1.0F, 1.0F, "obsidian_axe", Ref.tabExample);
  public static Item OBSIDIAN_SPADE = new ItemModSpade(obsidianMaterial, "obsidian_spade", Ref.tabExample);
  public static Item OBSIDIAN_HOE = new ItemModHoe(obsidianMaterial, "obsidian_hoe", Ref.tabExample);
  public static Item OBSIDIAN_SWORD = new ItemModSword(obsidianMaterial, "obsidian_sword", Ref.tabExample);
```

Now, to add another set of armor and tools, if you're so inclined, just create a new `ToolMaterial` or `ArmorMaterial` and create a new set of armor or tools in `ModItems`.

Lastly, we need to add all of this to our `RegisterItemHandler` like:

```java
  public static Item[] items = {
      ModItems.OBSIDIAN_INGOT, ModItems.HEALING_HERB,
      ModItems.OBSIDIAN_AXE, ModItems.OBSIDIAN_HOE, ModItems.OBSIDIAN_PICKAXE, ModItems.OBSIDIAN_SPADE, ModItems.OBSIDIAN_SWORD,
      ModItems.OBSIDIAN_HELM, ModItems.OBSIDIAN_CHEST, ModItems.OBSIDIAN_LEGS, ModItems.OBSIDIAN_FEET
  };
```

Back to the point, however, let's get to making all of these items out of our ingots using recipes...

Create the package `com.example.mem.recipes` and class `RecipeHandler`.

Recipes use a pretty simple format. each one is an array that contains 3 sets of 3 characters, followed by 1-3 associations to map the characters used into `Item`s. Think of the crafting table as:

```
[ ][ ][ ]
[ ][ ][ ]
[ ][ ][ ]
```
If you wanted to make a sword, you'd have:

```
[ ][I][ ]
[ ][I][ ]
[ ][S][ ]
```

Where I is an ingot and S is an `Items.STICK`.

Take that grid, and flatten it out to " I ", " I ", " S ", 'I', ingot, 'S', Items.STICK, and you've got a recipe constructor. Please note the single quotes around the 'I' and 'S' in the `Item`/character associations and double quotes around the strings.

Here's how our file looks in practice:

```java
public class RecipeHandler {
  public static void register() {
    registerToolRecipe(ModItems.OBSIDIAN_INGOT, ModItems.OBSIDIAN_PICKAXE, ModItems.OBSIDIAN_AXE, ModItems.OBSIDIAN_SPADE, ModItems.OBSIDIAN_HOE, ModItems.OBSIDIAN_SWORD);
    registerArmourRecipe(ModItems.OBSIDIAN_INGOT, ModItems.OBSIDIAN_HELM, ModItems.OBSIDIAN_CHEST, ModItems.OBSIDIAN_LEGS, ModItems.OBSIDIAN_FEET);
  }
  
  private static void registerToolRecipe(Item ingot, Item pickaxe, Item axe, Item shovel, Item hoe, Item sword) {
    GameRegistry.addRecipe(new ItemStack(pickaxe), new Object[] { "III", " S ", " S ", 'I', ingot, 'S', Items.STICK });
    GameRegistry.addRecipe(new ItemStack(axe), new Object[] { "II ", "IS ", " S ", 'I', ingot, 'S', Items.STICK });
    GameRegistry.addRecipe(new ItemStack(axe), new Object[] { " II", " SI", " S ", 'I', ingot, 'S', Items.STICK });
    GameRegistry.addRecipe(new ItemStack(shovel), new Object[] { " I ", " S ", " S ", 'I', ingot, 'S', Items.STICK });
    GameRegistry.addRecipe(new ItemStack(hoe), new Object[] { " II", " S ", " S ", 'I', ingot, 'S', Items.STICK });
    GameRegistry.addRecipe(new ItemStack(hoe), new Object[] { "II ", " S ", " S ", 'I', ingot, 'S', Items.STICK });
    GameRegistry.addRecipe(new ItemStack(sword), new Object[] { " I ", " I ", " S ", 'I', ingot, 'S', Items.STICK });
  }
  
  public static void registerArmourRecipe(Item ingot, Item helmet, Item chestplate, Item leggings, Item boots) {
    GameRegistry.addRecipe(new ItemStack(helmet), new Object[] { "III","I I","   ",'I',ingot});
    GameRegistry.addRecipe(new ItemStack(helmet), new Object[] { "   ","III","I I",'I',ingot});
    GameRegistry.addRecipe(new ItemStack(chestplate), new Object[] { "I I","III","III",'I',ingot});
    GameRegistry.addRecipe(new ItemStack(leggings), new Object[] { "III","I I","I I",'I',ingot});
    GameRegistry.addRecipe(new ItemStack(boots), new Object[] { "I I","I I","   ",'I',ingot});
    GameRegistry.addRecipe(new ItemStack(boots), new Object[] { "   ","I I","I I",'I',ingot});
  }
}
```

Last, we need to register our `RecipeHandler`, which (until I figure out which `RegistryEvent` handles it, if any) we put back in our `CommonProxy` class like:

```java
  public void init() {
    RecipeHandler.register();
  }
```

Add textures and json as per usual; See the [github repo](https://github.com/jmhutch/minecraft/tree/master/05-recipes) for examples.

Now if you run the client you should be able to grab a stack of ingots and sticks and create some Obsidian armor and tools.


