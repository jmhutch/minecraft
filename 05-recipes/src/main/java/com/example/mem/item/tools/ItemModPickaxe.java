package com.example.mem.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemModPickaxe extends ItemPickaxe {
	public ItemModPickaxe(ToolMaterial material, String name, CreativeTabs tab) {
		super(material);

		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().getResourcePath());
		this.setCreativeTab(tab);

		this.setMaxStackSize(1);
	}
}