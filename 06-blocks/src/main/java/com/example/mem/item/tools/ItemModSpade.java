package com.example.mem.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ItemModSpade extends ItemSpade {
	public ItemModSpade(ToolMaterial material, String name, CreativeTabs tab) {
		super(material);

		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().getResourcePath());
		this.setCreativeTab(tab);

		this.setMaxStackSize(1);
	}
}