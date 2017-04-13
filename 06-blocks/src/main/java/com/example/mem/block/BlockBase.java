package com.example.mem.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {

	protected String name;

	public BlockBase(Material materialIn, String name, float hardness, float resistance, float lightLevel,
			String toolType, int harvestLevel, CreativeTabs tab) {
		super(materialIn);

		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(tab);

		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setLightLevel(lightLevel);
		this.setHarvestLevel(toolType, harvestLevel);
		;

		this.name = name;
	}
}