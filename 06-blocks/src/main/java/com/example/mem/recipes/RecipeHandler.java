package com.example.mem.recipes;

import com.example.mem.block.ModBlocks;
import com.example.mem.item.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {
	public static void register() {
		registerToolRecipe(ModItems.OBSIDIAN_INGOT, ModItems.OBSIDIAN_PICKAXE, ModItems.OBSIDIAN_AXE,
				ModItems.OBSIDIAN_SPADE, ModItems.OBSIDIAN_HOE, ModItems.OBSIDIAN_SWORD);
		registerArmourRecipe(ModItems.OBSIDIAN_INGOT, ModItems.OBSIDIAN_HELM, ModItems.OBSIDIAN_CHEST,
				ModItems.OBSIDIAN_LEGS, ModItems.OBSIDIAN_FEET);
	    registerOreRecipes();
	}

	public static void registerOreRecipes() {
			GameRegistry.addShapedRecipe(new ItemStack(Blocks.OBSIDIAN), "###", "###", "###", '#', ModItems.OBSIDIAN_INGOT);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.OBSIDIAN_INGOT, 9), "   ", " # ", "   ", '#',
				Blocks.OBSIDIAN);

		GameRegistry.addSmelting(new ItemStack(ModBlocks.OBSIDIAN_ORE), new ItemStack(ModItems.OBSIDIAN_INGOT), 1);

	}
	
	private static void registerToolRecipe(Item ingot, Item pickaxe, Item axe, Item shovel, Item hoe, Item sword) {
		GameRegistry.addRecipe(new ItemStack(pickaxe),
				new Object[] { "III", " S ", " S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(axe), new Object[] { "II ", "IS ", " S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(axe), new Object[] { " II", " SI", " S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(shovel),
				new Object[] { " I ", " S ", " S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(hoe), new Object[] { " II", " S ", " S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(hoe), new Object[] { "II ", " S ", " S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(sword),
				new Object[] { " I ", " I ", " S ", 'I', ingot, 'S', Items.STICK });
	}

	public static void registerArmourRecipe(Item ingot, Item helmet, Item chestplate, Item leggings, Item boots) {
		GameRegistry.addRecipe(new ItemStack(helmet), new Object[] { "III", "I I", "   ", 'I', ingot });
		GameRegistry.addRecipe(new ItemStack(helmet), new Object[] { "   ", "III", "I I", 'I', ingot });
		GameRegistry.addRecipe(new ItemStack(chestplate), new Object[] { "I I", "III", "III", 'I', ingot });
		GameRegistry.addRecipe(new ItemStack(leggings), new Object[] { "III", "I I", "I I", 'I', ingot });
		GameRegistry.addRecipe(new ItemStack(boots), new Object[] { "I I", "I I", "   ", 'I', ingot });
		GameRegistry.addRecipe(new ItemStack(boots), new Object[] { "   ", "I I", "I I", 'I', ingot });
	}
}