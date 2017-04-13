package com.example.mem.block;

import com.example.mem.Ref;
import com.example.mem.item.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {

	/*
	 * for generic block without its own class call BlockBase directly, e.g.,
	 * registerBlock(ModBlocks.CAMO_MINE); new BlockBase(Material materialIn,
	 * String name, float hardness, float resistance, float lightLevel, String
	 * toolType, int harvestLevel, CreativeTabs tab)
	 */

	public static final Block OBSIDIAN_ORE = new BlockBase(Material.IRON, "obsidian_ore", 5F, 20F, 1.0F, "pickaxe", 3,
			Ref.tabExample);
	public static final ItemBlock ITEM_OBSIDIAN_ORE = new ItemBlock(OBSIDIAN_ORE);

	@Mod.EventBusSubscriber
	public static class RegisterBlockHandler {
		/*
		 * Register ModBlocks
		 */
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final Block[] blocks = { ModBlocks.OBSIDIAN_ORE };
			event.getRegistry().registerAll(blocks);
		}

		/*
		 * Register ItemBlocks
		 */
		@SubscribeEvent
		public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
			registerItemBlock(ModBlocks.ITEM_OBSIDIAN_ORE, ModBlocks.OBSIDIAN_ORE);

			event.getRegistry().registerAll(ModBlocks.ITEM_OBSIDIAN_ORE);
		}

		public static void registerItemBlock(ItemBlock item, Block block) {
			item.setRegistryName(block.getRegistryName());
		}

		/*
		 * Register textures
		 */
		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public static void registerModels(ModelRegistryEvent event) {
			registerBlock(ModBlocks.OBSIDIAN_ORE);
		}

		@SideOnly(Side.CLIENT)
		public static void registerBlock(Block block) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
					new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}

		@SideOnly(Side.CLIENT)
		public static void registerBlock(Block block, int meta, String fileName) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
					new ModelResourceLocation(fileName, "inventory"));
		}
	}

	public static class OreDictionaryHandler {
		public static void registerOreDictionary() {
			OreDictionary.registerOre("ingotObsidian", ModItems.OBSIDIAN_INGOT);
			OreDictionary.registerOre("oreObsidian", ModBlocks.ITEM_OBSIDIAN_ORE);
		}
	}
}
