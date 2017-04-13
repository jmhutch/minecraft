package com.example.mem.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemHealingHerb extends ItemFoodBase {
	public ItemHealingHerb(String name, CreativeTabs tab) {
	    super(5, 1.0F, true, new PotionEffect(MobEffects.REGENERATION, 400, 1));
	    
	    this.setRegistryName(name);
	    this.setUnlocalizedName(this.getRegistryName().getResourcePath());    
	    this.setCreativeTab(tab);
	  }
}