package com.example.mem.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

public class ItemModHoe extends ItemHoe {
	public ItemModHoe(ToolMaterial material, String name, CreativeTabs tab) {
		super(material);

		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().getResourcePath());
		this.setCreativeTab(tab);

		this.setMaxStackSize(1);
	}
}