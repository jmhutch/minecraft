---
title: Textures and json
permalink: /textures/
order: 5
comments: true
disqus: textures
---

To create textures you're going to want a decent graphics program along the lines of [Gimp](http://www.gimp.org) (which is free and runs on all major platforms) or I've heard [paint.net](https://www.getpaint.net/) is also good. In short, you need a graphics editor that supports transparent backgrounds, can edit at the pixel level (zoom), and save as .png format.

Lacking the means or desire to make your own textures, you can always just copy some existing textures from vanilla Minecraft. Just extract the Minecraft jar file somewhere and navigate to the assets folder, then into textures and items. The same holds true for when we work with `Block`s or `Entity`s.

To start, create the folder `src/main/resources` if it doesn't exist (your main code being in src/main/java, for reference).

Under resoources, add the packages `assets.mem.blockstates`, `assets.mem.models.block`, `assets.mem.models.item`, `assets.mem.textures.blocks`, and `assets.mem.textures.items`.

Yes, you'll need them all, and yes, I meant to make 'item' and 'block' singular under models and plural under textures. You can technically call the textures packages whatever you want, but this is how vanilla and the vast majority of mods organize things.

Under `assets.models.item` create `obsidian_ore.json` with:

```json
{
  "parent": "item/generated",
    "textures": {
      "layer0": "mem:items/obsidian_ingot"
    }
}
```

I'll explain this in a moment.

In `assets.textures.items` add `obsidian_ingot.png` (your own or a "borrowed" image).

This file must be a png; if you get a 'missing texture' error and the file is definitely there, triple check that it's _definitely_ a png file (not a renamed jpg or something).

If you haven't yet, under `assets.lang` create a file `en_US.lang` with:

```
item.obsidian_ingot.name=Obsidian Ingot
```

To support multiple languages you can add the apporpriate file.lang for each with a list of strings for all items, blocks, and other lablelled things in your mod. For now we'll support the default of American English. If you're in game and see an ugly label on something, take note of it and put it in this .lang file to give it a nice, pretty name instead.

Back to that json file...

You can see we're setting the path to our texture as our namespace followed by `items/obsidian_ingot`. This tells the game to look under `assets.mem.items` for the file `obsidian_ingot.png`. 

If we dig through an extracted Minecraft jar (I can't get jsons to open from the resources in eclipse)for `item/generated` to see what that "parent" line is referring to, we'll find `generated.json` looks like this:

```json
{
    "parent": "builtin/generated",
    "display": {
        "ground": {
            "rotation": [ 0, 0, 0 ],
            "translation": [ 0, 2, 0],
            "scale":[ 0.5, 0.5, 0.5 ]
        },
        "head": {
            "rotation": [ 0, 180, 0 ],
            "translation": [ 0, 13, 7],
            "scale":[ 1, 1, 1]
        },
        "thirdperson_righthand": {
            "rotation": [ 0, 0, 0 ],
            "translation": [ 0, 3, 1 ],
            "scale": [ 0.55, 0.55, 0.55 ]
        },
        "firstperson_righthand": {
            "rotation": [ 0, -90, 25 ],
            "translation": [ 1.13, 3.2, 1.13],
            "scale": [ 0.68, 0.68, 0.68 ]
        },
        "fixed": {
            "rotation": [ 0, 180, 0 ],
            "scale": [ 1, 1, 1 ]
        }
    }
}
```

From this we can glean that items that inherit from this type have a specific rotation, translation, and scale when viewed on the ground, when worn as a hat, and in first and third person. This is important. For an ingot, this view works well, but if you take something like a stick and try and use this as a parent it won't work - you get the purple and black checkers. For that, you need to use the parent `item/handheld` (which inherits from `item/generated` but overrides its view settings).

`item/generated`, in turn, inherits from `builtin/generated`, which I haven't figured out how to find yet, but you get the idea.

We'll revisit texture json files in more detail when we cover [`Block`s](/blocks/), which get significantly more involved.

For now, if you've created the approrpiate json file, placed a png in the proper place, and, for good measure, created a nicely formatted language string, you should be able to start up the client and see your item in game - texture and all now!

If you don't have any textures handy there are sample textures in the [github repo](https://github.com/jmhutch/minecraft) associated with these tutorials.

