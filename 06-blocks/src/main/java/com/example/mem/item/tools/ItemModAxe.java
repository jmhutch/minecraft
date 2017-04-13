package com.example.mem.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class ItemModAxe extends ItemAxe {
	public ItemModAxe(ToolMaterial material, float dmg, float speed, String name, CreativeTabs tab) {
		super(material, dmg, speed);

		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().getResourcePath());
		this.setCreativeTab(tab);

		this.setMaxStackSize(1);
	}
}