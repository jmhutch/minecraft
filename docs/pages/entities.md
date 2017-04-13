---
title: Creating Entities
permalink: /entities/
order: 14
comments: true
disqus: entities
---

Entities - one of the least documented (that I've seen), and more complex aspects of Minecraft modding.

An `Entity` differs from a `TileEntity` in that it's not bound to a spot. Examples of `Entity` objects are mobs, farm animals, and villiagers. They may be spawned with eggs, rather than placed as a `Block`, may wander, have unique AIs (such as being attracted to or avoiding certain world features or other entities), attack or be attacked and/or killed.

To quote another [guide](http://jabelarminecraft.blogspot.com/p/creating-custom-entities.html) on the subject:
>A living entity is actually fairly complex, in that it needs to have a textured model that gets automatically rendered, that probably moves (perhaps with artificial intelligence), is probably animated (with model and perhaps texture), makes sound, has a name, spawns naturally, has a spawn egg, etc.

An `Entity` consists of several parts:
- a class that extends `Entity` and defines an `Entity` ID, specifies its AI and other behavoirs and overrides various methods to enable other characteristics (EntityFoo.java)
- a class that extends `ModelBase` and defines the box design other model attributes of the object (ModelFoo.java)
- a renderer class that asssociates the model with a texture and adds any GL attributes or other rendering instructions (RenderFoo.java)
- a texture map or maps and associated animations

>Your custom entity should be an extension of some vanilla entity kind of class, but it is important to figure out which one is best on which to base your entity. 

>Some major features of the vanilla entity base classes and interfaces to help you choose:
> - EntityCreature is an EntityLiving that has AI (it moves with path-finding and attacks). 
> - EntityAgeable is an EntityCreature that can come in various sizes/ages. 
> - EntityAnimal is an EntityAgeable that enables breeding by implementing IAnimal.
> - EntityTameable is EntityAnimal that implement IEntityOwnable 



