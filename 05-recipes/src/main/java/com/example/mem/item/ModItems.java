package com.example.mem.item;

import com.example.mem.Ref;
import com.example.mem.item.tools.ItemModAxe;
import com.example.mem.item.tools.ItemModHoe;
import com.example.mem.item.tools.ItemModPickaxe;
import com.example.mem.item.tools.ItemModSpade;
import com.example.mem.item.tools.ItemModSword;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	public static final ArmorMaterial obsidianArmorMaterial = EnumHelper.addArmorMaterial("obsidian",
			Ref.MODID + ":obsidian", 50, new int[] { 10, 10, 10, 10 }, 50, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 5.0F);
	public static final ToolMaterial obsidianMaterial = EnumHelper.addToolMaterial(Ref.MODID + ":obsidian", 0, 1000,
			1.0F, 5.0F, 100);

	public static Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot", Ref.tabExample);
	public static Item HEALING_HERB = new ItemHealingHerb("healing_herb", Ref.tabExample);

	public static ItemModArmor OBSIDIAN_HELM = new ItemModArmor(obsidianArmorMaterial, 1, EntityEquipmentSlot.HEAD,
			"obsidian_helm", Ref.tabExample);
	public static ItemModArmor OBSIDIAN_CHEST = new ItemModArmor(obsidianArmorMaterial, 1, EntityEquipmentSlot.CHEST,
			"obsidian_chest", Ref.tabExample);
	public static ItemModArmor OBSIDIAN_LEGS = new ItemModArmor(obsidianArmorMaterial, 2, EntityEquipmentSlot.LEGS,
			"obsidian_legs", Ref.tabExample);
	public static ItemModArmor OBSIDIAN_FEET = new ItemModArmor(obsidianArmorMaterial, 1, EntityEquipmentSlot.FEET,
			"obsidian_feet", Ref.tabExample);

	public static Item OBSIDIAN_PICKAXE = new ItemModPickaxe(obsidianMaterial, "obsidian_pickaxe", Ref.tabExample);
	public static Item OBSIDIAN_AXE = new ItemModAxe(obsidianMaterial, 1.0F, 1.0F, "obsidian_axe", Ref.tabExample);
	public static Item OBSIDIAN_SPADE = new ItemModSpade(obsidianMaterial, "obsidian_spade", Ref.tabExample);
	public static Item OBSIDIAN_HOE = new ItemModHoe(obsidianMaterial, "obsidian_hoe", Ref.tabExample);
	public static Item OBSIDIAN_SWORD = new ItemModSword(obsidianMaterial, "obsidian_sword", Ref.tabExample);
		
	@Mod.EventBusSubscriber
	public static class RegisterItemHandler {
		public static Item[] items = {
			ModItems.OBSIDIAN_INGOT, ModItems.HEALING_HERB,
			ModItems.OBSIDIAN_AXE, ModItems.OBSIDIAN_HOE, ModItems.OBSIDIAN_PICKAXE, ModItems.OBSIDIAN_SPADE, ModItems.OBSIDIAN_SWORD,
			ModItems.OBSIDIAN_HELM, ModItems.OBSIDIAN_CHEST, ModItems.OBSIDIAN_LEGS, ModItems.OBSIDIAN_FEET
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