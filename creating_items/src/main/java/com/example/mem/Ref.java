package com.example.mem;

import com.example.mem.item.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Ref {
  public static final String MODID = "mem";
  public static final String MOD_NAME = "My Example Mod";
  public static final String VERSION = "0.0.1";

  public static CreativeTabs tabExample = new CreativeTabs("tab_Example") {
	  @Override
	  public ItemStack getTabIconItem() {
	    return new ItemStack(ModItems.OBSIDIAN_INGOT);
	  }
  };
}

