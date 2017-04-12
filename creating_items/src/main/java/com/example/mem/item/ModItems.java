package com.example.mem.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	public static Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot", CreativeTabs.BUILDING_BLOCKS);

	@Mod.EventBusSubscriber
	public static class RegisterItemHandler {

		public static Item[] items = {
				ModItems.OBSIDIAN_INGOT
		};
		
		/*
		 * Register ModItems
		 */
	    @SubscribeEvent
	    public static void registerItems(RegistryEvent.Register<Item> event) {
	    	for(Item item : items) {
	    		event.getRegistry().register(item);
	       	}
	    }

	    /*
	     * Register Textures
	     */
	    @SubscribeEvent
	    @SideOnly(Side.CLIENT)
	    public static void registerModels(ModelRegistryEvent event) {
	    	for(Item item : items ) {
	    		registerItem(item);
	    	}
	    }
	    
	    public static void registerItem(Item item) {
	        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	    }
	    
	    public static void registerItem(Item item, int meta, String fileName) {
	        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(fileName, "inventory"));
	    }
	}
}