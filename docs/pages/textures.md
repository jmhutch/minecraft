---
title: Textures and json
permalink: /textures/
order: 5
comments: true
disqus: textures
---

create folder `src/main/resources`

add packages `assets.blockstates`, `assets.lang`, `assets.models`, `assets.models.block`, `assets.models.item`, `assets.textures`, `assets.textures.blocks`, and `assets.textures.items`

yes, you'll need them all.

under `assets.models.item` create `obsidian_ore.json` with:

```json
{
  "parent": "item/generated",
    "textures": {
      "layer0": "mem:items/obsidian_ingot"
    }
}
```

under `assets.textures.items` add `obsidian_ingot.png`

file must be png; if you get a 'missing texture' error and the file is definitely there, triple check that it's _definitely_ a png file (not a renamed jpg or something).

Under `assets.lang` create a file `en_US.lang` with:

```
item.obsidian_ingot.name=Obsidian Ingot
```

Lots of stuff here about parent jsons, what the things mean and everything else...

