# Life
Life is a mod developed for Fabric (current: 1.21.1) and implements a peaceful and normal life.

In contrast to the usual survival experience, Life focuses on calm, day-to-day living. Players can build homes, grow gardens, take care of animals, and engage in immersive, non-combat activities such as cooking, fishing, crafting furniture, and interacting with friendly villagers.

Life is perfect for players who enjoy roleplay, relaxation, or building without the pressure of survival threats. Whether you're creating a quiet homestead or a bustling peaceful village, Life provides the tools and ambiance to make it your own.

## Key features include (only found in addons - in order of release):

### Liveable LifeWare
- Wear outfits to look decorated and good-looking

### Caves of the Future
- Explore the Cave Systems to find new ores of the future age

### Forgeries
- Smelt metals of Life and craft tools and weapons to help with your journeys of the history.


### Expanded Farming
- Plant dozens of new fruits and vegetables, and care for them with realistic growth stages.

### Home & Hearth
- Decorate your home with custom furniture, wallpapers, and lighting for a cozy atmosphere.

### Healthy Lives (Relationships)
- Build friendships with villagers, help them with tasks, and unlock unique dialogue and events.

## Addon Support

We are planning on releasing our first addon to connect to this mod and proceed to upload a template to develop your own custom mods for Life but check the wiki for help.

By the way if you don't want to you can use on the latest version...

```
	repositories {
        maven {
            name = 'Ladysnake Mods'
            url = 'https://maven.ladysnake.org/releases'
        }
        maven {
		    name = 'Modrinth'
		    url = 'https://api.modrinth.com/maven'
		    content {
			    includeGroup 'maven.modrinth'
		    }
	    }
	    
    }
	
	dependencies {
	    // other mods here (e.g Fabric, Cloth Config or Mod Menu)
	    
	    // Cardinal Components API for data storage (Life depends on this)
	    modImplementation "org.ladysnake.cardinal-components-api:cardinal-components-base:4.1.1"
	    modImplementation "org.ladysnake.cardinal-components-api:cardinal-components-entity:4.1.1"

	    include "org.ladysnake.cardinal-components-api:cardinal-components-base:4.1.1"
	    include "org.ladysnake.cardinal-components-api:cardinal-components-entity:4.1.1"
	
	    // Life (This is required)
	    modImplementation 'maven.modrinth:life-mod:Alpha-0.1.5'
	}
```

Get the template [HERE](https://github.com/Fabricators-of-Life/Life-addonTemplate)

## Supported Versions
We contain a list of version most people may ask for but in our current situation this will take a bit longer

| Version  | Supported | Continued Development |
|----------|-----------|-----------------------|
| 1.21.1   | Yes       | Yes                   |
| 1.21.2-6 | No        | None                  |
| 1.22     | No        | Not till release      |

## Changelog

Shows the last 3 Updates

### Alpha-0.1.3
- Reorganised Folders
- Adding more Mod Requirements

### Alpha-0.1.4
- Registrator has been renamed to Registrar
- Implemented Inventory has been added in for easier mod use
- Respective Icons Shown for the Stat Checker

### Alpha-0.1.5
- Added Addon Compatibility
- Registrar renamed to SimpleRegistrar
- Changed Addon Advancement Icon from Oak Sapling to Spruce Sapling
- LifeClient had a cleanup
- Change Stats Keybinding from 'G' to 'C'
- Cheese added as a placeholder