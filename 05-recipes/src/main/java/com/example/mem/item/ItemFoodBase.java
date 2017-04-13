package com.example.mem.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFoodBase extends ItemFood {
	PotionEffect[] effects;

	public ItemFoodBase(int amount, boolean isWolfFood, PotionEffect... potienEffects) {
		super(amount, isWolfFood);
		this.effects = potienEffects;
	}

	public ItemFoodBase(int amount, Float saturation, boolean isWolfFood, PotionEffect... potionEffects) {
		super(amount, saturation, isWolfFood);
		this.effects = potionEffects;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		for (PotionEffect effect : effects) {
			player.addPotionEffect(effect);
		}
	}
}