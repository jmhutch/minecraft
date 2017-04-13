package com.example.mem.proxy;

import com.example.mem.recipes.RecipeHandler;

public class CommonProxy {
	public void preInit() { }

	public void init() {
	    RecipeHandler.register();
	}
	
	public void postInit() { }
}