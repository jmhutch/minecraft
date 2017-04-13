package com.example.mem.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemModSword extends ItemSword {
	public ItemModSword(ToolMaterial material, String name, CreativeTabs tab) {
		super(material);

		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().getResourcePath());
		this.setCreativeTab(tab);

		this.setMaxStackSize(1);
	}
}