package com.example.mem;

import com.example.mem.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Ref.MODID, version = Ref.VERSION, name = Ref.MOD_NAME)
public class ExampleMod
{
  @Instance(Ref.MODID)
  public static ExampleMod INSTANCE;

  @SidedProxy(clientSide = "com.example.mem.proxy.ClientProxy", serverSide = "com.example.mem.proxy.CommonProxy")
  public static CommonProxy proxy;

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    proxy.preInit();
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    proxy.init();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    proxy.postInit();
  }
}
