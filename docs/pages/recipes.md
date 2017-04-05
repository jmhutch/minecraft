---
title: Recipes
permalink: /recipes/
order: 6.5
comments: true
disqus: recipes
---

To illustrate recipes we're going to take our Obsidian Ingot `Item`, add a few classes for Armor and Tool creation, then create recipes to turn Ingots into useful Stuff.

Armor is the easiest, since we only need to extend `ItemArmor` for all 4 pieces. So in `com.example.mem.items` create the class `ItemModArmor` with:

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

Note the second param, `renderIndexIn` is set to 2 for leggings, that's because their texture is on a separate layer than the rest of the pieces. Refer to the (Textures and json)[/textures/] article for more information.

Next the tools. Each inherits from its own subclass, so each will want its own class. You could be clever and inherit from the base class `ItemTool` (and may want to in some cases when customizing a tool heavily) and create a generic tool class, but creating separate classes gives us basic tool functionality for each type for free, and our classes can be reused indefinitely for different material types, so it's a reasonable investment.

