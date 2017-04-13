---
title: Creating Blocks
permalink: /blocks/
order: 7
comments: true
disqus: blocks
---

We'll go about creating `Block`s essentially the same way as creating `Item`s. That is, we'll create a base class that will extend vanilla's `Block` class, set up our registration class and methods, and add json and textures. Where `Block`s differ from `Item`s, firstly, is that they have 2 representations - as an inventory `Item` and as a placable `Block` in the `World`. The object that bridges the gap between `Item` and `Block` is called an `ItemBlock`, and one must be registered for each `Block` registered in order for your `Block` to display correctly in your inventory.

The second difference between `Block`s and `Item`s is that `Block`s have states. A `Block` faces a certain direction when placed, which can be paid attention to or ignored. It may have 'active' and 'inactive' states, or any number of other attributes upon which we can test for and act upon. The concept of Blockstates is fairly complex and will be covered in its own [chapter](/blockstates/).

One more major difference is that a `Block` can have a `TileEntity` associated with it, which allows the `Block` to store data, contain storage, and other fun and interesting things. This also will be covered in its own [chapter](/tileentities).

So, let's get started with just the basics. First we'll create a new package `com.example.mem.block`, and add our base class, `BlockBase` to it. This class should look like:

```java
public class BlockBase extends Block {
  
  protected String name;

  public BlockBase(Material materialIn, String name, float hardness, float resistance, float lightLevel, String toolType, int harvestLevel, CreativeTabs tab) {
    super(materialIn);
    
    this.setRegistryName(name);
    this.setUnlocalizedName(name);
    this.setCreativeTab(tab);
    
    this.setHardness(hardness);
    this.setResistance(resistance);
    this.setLightLevel(lightLevel);
    this.setHarvestLevel(toolType, harvestLevel);;
    
    this.name = name;
  }
}
```

You can see it's very similar to our `Item` base class, with the addition of a few settings for hardness, resistance, light level and harvest type/level. There are also, of course, numerous different methods you can override related to doing `Block`y things, but that's beyond the scope of this chapter.

Again, as with `Item`s, we'll create a class called `ModBlocks` where we'll instantiate our public static constants for each `Block` and register them in an inner class. We'll create an Obsidian Ore `Block` to go with our Ingot, so we can then use it also to demonstrate smelting. We'll ignore the vanilla Obsidian `Block`s for now and make ours a little more interesting.

```java
public class ModBlocks {
  
  /*
   * for generic block without its own class call BlockBase directly, e.g.,
        registerBlock(ModBlocks.CAMO_MINE);
   * new BlockBase(Material materialIn, String name, float hardness, float resistance, float lightLevel, String toolType, int harvestLevel, CreativeTabs tab)
   */

  public static final Block OBSIDIAN_ORE = new BlockBase(Material.IRON, "obsidian_ore", 5F, 20F, 1.0F, "pickaxe", 3, Ref.tabExample);
  public static final ItemBlock ITEM_OBSIDIAN_ORE = new ItemBlock(OBSIDIAN_ORE);

  @Mod.EventBusSubscriber
  public static class RegisterBlockHandler {
    /*
     * Register ModBlocks
     */
      @SubscribeEvent
      public static void registerBlocks(RegistryEvent.Register<Block> event) {
        final Block[] blocks = {ModBlocks.OBSIDIAN_ORE};
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

```

Ok, so we did quite a lot there. I'd like to take a moment to point out the `@SideOnly(Side.CLIENT)` annotations sprinkled liberally around in our registration classes. In a nutshell, when you're running a standalone server it will not have access to _any_ rendering or graphics related classes, so, since all code executes in both the client and server threads, sometimes we need to tell one or the other to ignore something, lest we crash the server horribly. These annotations simply say we want to only run the following method on the client side. This is equivalent to calling registration through the proxies and only added those functions to the `ClientProxy`, but since we're using events that's not really feasible in this scenario without moving all the registery events and code into the proxy handlers, which just feels cluttered and complicated to me.

We pass in a few more params than for `Item`s. The first is `Material materialIn` which gives the `Block` certain attributes depending on which type of material you select. TODO: find reference to Material types and what attributes each provides. 

Hardness is how many times you have to whack your `Block` to break it, resistance is how well your `Block` will hold up to nearby explosions, lightLevel is how much (if any) light your block emits. TODO: Look up glowstone and other blocks to find (max) light level values. I have our Obsidian Ore `Block` emitting a nice glow so that it'll be easy to find when we go over ore generation in [World Generation](/worldgen/), and because it's just cool to have a glowing black chunk of ore.

Last, toolType is the type of tool required to harvest (vs break/destroy) this block, and harvestLevel is 0-3 representing `wood`, `stone`, `iron` and `diamond` difficulty, denoting what level of tool you need to harvest the `Block`. Our Obisidian Ore requires a `diamond pickaxe` or better, naturally.

As noted in the comments, for simple `Block`s you can just call the `BlockBase` class directly and pass in the required params. If you want a more complex `Block` with more attributes set or overrides (including onActivation()) you'll want to create a separate class, such as `BlockObsidianOre` which might look like:

```java
public class BlockObsidianOre extends BlockBase {
  public BlockObsidianOre(String name) {
    super(Material.ROCK, name, 5F, 20F, 1.0F, "pickaxe", 3, Ref.tabExample);
  }
}

```

Passing the params to `super()` from the class instead, and calling it from `ModBlocks` more simply, as:

```java
  public static final Block OBSIDIAN_ORE = new BlockObsidianOre("obsidian_ore");

```

This leaves you with a lot more flexibility to expand your `Block` and its features, but isn't necessary for simple `Block`s like most ore that just sits there and gets harvested. It's mostly just an organization/personal preference thing.

We should also take a quick trip back to [Recipes](/recipes/) real fast and add a smelting recipe to turn our new ore into ingots.

In `RecipeHandler` add

```java
    registerOreRecipes();
```

to the bottom of your `register` method, then below that method add:

```java
  public static void registerOreRecipes() {
    GameRegistry.addShapedRecipe(new ItemStack(Blocks.OBSIDIAN),
        "###", "###", "###", '#', ModItems.OBSIDIAN_INGOT);
    GameRegistry.addShapedRecipe(new ItemStack(ModItems.OBSIDIAN_INGOT, 9),
        "   ", " # ", "   ", '#', Blocks.OBSIDIAN);

    GameRegistry.addSmelting(new ItemStack(ModBlocks.OBSIDIAN_ORE), 
        new ItemStack(ModItems.OBSIDIAN_INGOT), 1);
    
  }
```

This also lets you take 9 ingots and to make a vanilla block of Obsidian, or turn an Obsidian block into 9 ingots, just like most other types of ore/ingots work. The notable method is `addSmelting()` which takes params of the `Item` to smelt, the resulting `ItemStack`, which in turn takes params of the type of `Item` in the stack and number of `Item`s. Changing the 1 to 5, for instance, would yeild 5 ingots per ore. 

`ItemStack`s, it should be noted, are how all `Item`s in your inventory (or any other inventory) are represented in the game. We register an `Item`, but when we use it, it's an `ItemStack`, even if it's only a stack of 1. As of 1.11, basically anywhere you'll represent or pass around `Item` objects you will do so via `ItemStack`s, and likewise that's what'll be returned from `Item` returning methods.

At this point, you can add appropriate json and texture files (and your en_US.lang entry - `tile.obsidian_ore.name`) and your `Block` should be visible, placable, and harvestable in your test client. Note that you'll need json and texture files for both `Block` and `Item` objects for the placable vs inventory differences mentioned above, and you'll need to add a package `assets.mem.blockstates` and toss this json in there:


```json
{
    "variants": {
        "normal": {
            "model": "mem:obsidian_ore"
  }
     }
}

```


We'll get to blockstates later, so just know this says we have one varient of the `Block`, 'normal', and we list its model. One texture can be referenced by both `Block` and `Item` json, but all 3 json files are required.

Speaking of textures and json, this is where things start to get a lot more interesting. If you'd like to learn more about json and textures, read on. If you just want to make your `Block` and move on to the next topic, this chapter effectively ends here.


You can define blocks of varying complexity and detail using json alone, and in fact this seems to be the preferred method of doing so (vs java-based models). The only time you need to venture outside of json for modelling in 1.11 is to make entities that have moving parts. You can animate your textures with json alone, but only if they have no moving parts. There's aparently even a way to do that, but the method involved is depreciated, so we'll just pretend there isn't.

Let's dive in a little bit and take a look at how json moddelling works.

For our Obsidian Ore `Block`, our json files will look like this:

```json
 {
    "parent": "block/cube_all",
    "textures": {
      "all": "mem:blocks/obsidian_ore"
    }
}
```

You may have noticed (and/or ignored) the line `"parent": "block/cube_all"`. This means we inherit from the minecraft assets namespace, from the `Block` object `cube_all`. If we dig into vanilla we see that `cube_all` is defined as:

```json
{
    "parent": "block/cube",
    "textures": {
        "particle": "#all",
        "down": "#all",
        "up": "#all",
        "north": "#all",
        "east": "#all",
        "south": "#all",
        "west": "#all"
    }
}
```

Somewhat cryptic, but you can get a basic idea. `cube_all` has the same texture on all sides, and the same texture for its particle effect (the texture particles you see exploding out when a block is broken), all defined by the reference variable '#all'. Hashmarks in front of variables or paths in json are known as json pointers, and refer to snippets defined somewhere else in the json schema, generally speaking. This is a shortcut notation to re-use pieces of json in multiple places. This allows us to define a texture once and re-use it on all sides without defining each side in our json.

In this case, `"#all"` is referencing the texture we pass in from our json: `"all": "mem:blocks/obsidian_ore"`. When you extend a json model by inheriting from it, attributes you specify will be interpolated if they're referenced in the parent object. It's a little like inheritance and overriding in Java, conceptually speaking.

If we wanted a `Block` that had different textures on different sides, we wouldn't want to inherit from `cube_all`. We could instead follow the chain of inheritance from `cube_all` to `block_cube` which defines different textures for each face:

```json
{
    "parent": "block/block",
    "elements": [
        {   "from": [ 0, 0, 0 ],
            "to": [ 16, 16, 16 ],
            "faces": {
                "down":  { "texture": "#down", "cullface": "down" },
                "up":    { "texture": "#up", "cullface": "up" },
                "north": { "texture": "#north", "cullface": "north" },
                "south": { "texture": "#south", "cullface": "south" },
                "west":  { "texture": "#west", "cullface": "west" },
                "east":  { "texture": "#east", "cullface": "east" }
            }
        }
    ]
}
```

Now we're starting to see actual shape definitions! This is the simplest case possible, drawing a box from point x=0, y=0, z=0 to point x=16, y=16, z=16, or, in simpler terms, a 16x16 solid cube, which would take up one full `Block` square in the `World`. The "faces" attribute defines textures and cullface parameters for each side of the cube. If we inherited from this model we could specify attributes for each face and it would interpolate them into this model for its same-named attributes.

This model inherits from `block/block` which contains:

```json
{
    "display": {
        "gui": {
            "rotation": [ 30, 225, 0 ],
            "translation": [ 0, 0, 0],
            "scale":[ 0.625, 0.625, 0.625 ]
        },
        "ground": {
            "rotation": [ 0, 0, 0 ],
            "translation": [ 0, 3, 0],
            "scale":[ 0.25, 0.25, 0.25 ]
        },
        "fixed": {
            "rotation": [ 0, 0, 0 ],
            "translation": [ 0, 0, 0],
            "scale":[ 0.5, 0.5, 0.5 ]
        },
        "thirdperson_righthand": {
            "rotation": [ 75, 45, 0 ],
            "translation": [ 0, 2.5, 0],
            "scale": [ 0.375, 0.375, 0.375 ]
        },
        "firstperson_righthand": {
            "rotation": [ 0, 45, 0 ],
            "translation": [ 0, 0, 0 ],
            "scale": [ 0.40, 0.40, 0.40 ]
        },
        "firstperson_lefthand": {
            "rotation": [ 0, 225, 0 ],
            "translation": [ 0, 0, 0 ],
            "scale": [ 0.40, 0.40, 0.40 ]
        }
    }
}
```

This gets a little verbose, but you should still be able to glean what it's doing. Basically, it's setting up how a `Block` will display in various situations, such as sitting on the ground, from first person, third person, etc.. You can override these to do fun things with scale, for instance, for your own objects. This json doesn't inherit from anything, and thus is a base definiton. 

Just for fun, let's look at another example that's a little more interesting than a cube. If we look at brown_mushroom.json, for instnace, it contains:

```json
{
    "parent": "block/cross",
    "textures": {
        "cross": "blocks/mushroom_brown"
    }
}
```

Its parent, `block/cross` is defined as:

```json
{
    "ambientocclusion": false,
    "textures": {
        "particle": "#cross"
    },
    "elements": [
        {   "from": [ 0.8, 0, 8 ],
            "to": [ 15.2, 16, 8 ],
            "rotation": { "origin": [ 8, 8, 8 ], "axis": "y", "angle": 45, "rescale": true },
            "shade": false,
            "faces": {
                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross" },
                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross" }
            }
        },
        {   "from": [ 8, 0, 0.8 ],
            "to": [ 8, 16, 15.2 ],
            "rotation": { "origin": [ 8, 8, 8 ], "axis": "y", "angle": 45, "rescale": true },
            "shade": false,
            "faces": {
                "west": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross" },
                "east": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross" }
            }
        }
    ]
}
```

Here you can see examples of rendering instructions (ambientocclusion), 2 boxes defined (and very not-cube this time), and uv texture mapping instructions. This definition includes the 'rotation' instructions we saw in the base `block` json, the box definitions, texture mapping, texture definition, and rendering instructions all in one model and has no parent. It's still a very simple example, having only 2 elements (boxes), but it gives you a good idea of what's possible using json and textures alone to create myraid types of simple to complex `Block` shapes.

There are a number of tools and visual editors available to help build and tweak your models with the aid of visualization, then you can (sometimes) export as json (or Java for animated entities) or use the coordinates to write your json by hand. Other people simple write this (and Java models) by hand using tools no more sophisticated than graph paper to map out their objects.

Some of the better, more up to date (if not necessarily 100% so) tools are [Mr Crayfish's Model Editor](https://mrcrayfish.com/tools?id=mc) which is listed as 1.8 compatible, but should still work fine, [Cubik Studio](https://cubik.studio/) which costs about $20, which is very reasonable really, and is very powerful and flexible, and [Tabula Mod](https://minecraft.curseforge.com/projects/tabula-minecraft-modeler) which needs to run as a Minecraft mod under 1.8 (yes, it runs in the game client, strangely enough) but is the only tool I can find that exports Java models (which we'll cover more later) and is considered the successor to the once dominant and now disappeared Techne modeller.

We'll come back to this discussion later for [Tile Entities](/tileentities/) and [Entities](/entities/). For now explore, experiement, enjoy! :sparkles:





