package com.example.mem.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemModArmor extends ItemArmor {
	public ItemModArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name,
			CreativeTabs tab) {
		super(materialIn, renderIndexIn, equipmentSlotIn);

		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().getResourcePath());
		this.setCreativeTab(tab);

		this.setMaxStackSize(1);
	}

}