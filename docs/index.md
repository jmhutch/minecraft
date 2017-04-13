---
layout: default
title: Home
permalink: /
order: 1
---

### :exclamation: This site is a Work In Progress - any content may be incorrect and/or incomplete for the time being.

### Welcome!


My goal is to provide an updated (1.11 presently) guide to minecraft modding that falls between the common "type this, then this..." youtube tutorial that gives you (hopefully) working examples but little to no explanation or theory - and the typical forum response of "look it up in the vanilla code". I'll provide examaples and references to vanilla code, along with explanations to the best of my ability - not 100% handholding, but neither throwing you in the lake to learn to swim, either.


As an experienced programmer learning Minecraft modding myself after many years of mod use (and abuse?), I find it's the best time to document the process - as you go through it - since you notice all the details that tend to be glossed over with experience, and have to do a good deal of research as it is, might as well document it!


Note: General Java knowledge and understanding of OO programming principles overall is required, not just encouraged, to do more than cut & paste the most trivial bits of code into a functioanl mod. Minecraft modding is predicated on the concept of extending and enhancing the vanilla code, while making use of the Forge enhancements (if desired). 

Most modding is done by finding a vanilla class that closely meets your needs, copying or (more likely) extending it, overriding some useful methods and possibly adding your own new functionality to it. A better-than-vague understanding of Java objects, inheritance, and interfaces, at the least, will provide useful for trivial tasks and absolutely necessary for complex undertakings. 

Concepts such as collections, abstract classes, and syntactical sugar like Java 8 lambda expressions are not necessary, but will make your life easier. Overall undderstanding of code design, refactoring, and organization principles will make your life much, much easier if you plan to write anything more than a trivial mod that adds a few ores or non-functional items or the like.

You have been warned. Java Minecraft modding is not something you can learn in a vacuum - you have to actually learn to program as a whole separately. Trying to cheat your way through leads to 'cargo cult cut and pasting' (copying code you don't actually understand to try and replicate an effect written by somebody else), and a smattering of really bad tutorials that end up parroting bad or wrong information because they don't understand it, but rather just memorize and regurgitate in a long cycle of misinformation perpetuation from on youtuber to the next. This helps nobody and has caused a lot of people's problems I see on the forums. **Do your best to understand _every_ line of code you write, and why**.


ToDo:

- [x] Setup Guide - install Eclipse, get Forge, set up workspace
- [x] Getting Started - basic mod setup/boilerplate and common code structure
- [x] Items - create custom items
- [x] Textures and json - what, where, how
- [x] CreativeTabs - quick but necessary... gotta have custom tabs
- [x] Making Food - yum?
- [x] Recipes
- [ ] Blocks - create custom blocks and events for them
- [ ] Blockstates - exciting topic everybody loves!
- [ ] Events - make things do stuff
- [ ] TileEntities - aka how to associate data with blocks
- [ ] TileEntities with storage - see above, plus storage!
- [ ] Crops
- [ ] Networking - now that we have data, we need to sync it
- [ ] Capbilities
- [ ] Tools - sidestep into modelling tools and utils to prepare for...
- [ ] Entities - that topic every other tutorial avoids! Models, Entities, Renderers, oh my!
- [ ] Gui stuff - UIs for things.
- [ ] WorldGen - Add Ore, Trees, etc. and modify biomes in general
- [ ] Dimensions - playing with dimensional creation and travel
- [ ] Achievements - it'll be one if i get this far...
- [ ] Config stuff
- [ ] Creating an API
- [x] Update this list with whatever else I've missed...
- [x] Add comment/discussion system to article pages
