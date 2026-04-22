# Triggers

<a href='https://files.minecraftforge.net'><img alt="forge" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/forge_vector.svg"></a>
<a href='https://fabricmc.net'><img alt="fabric" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/fabric_vector.svg"></a>
<a href='https://neoforged.net/'><img alt="neoforge" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/neoforge_vector.svg"></a>

## About

**Triggers** is a library mod that provides a unified, modular, Forge-style event API for in-game triggers and
advancement criteria. It abstracts standard Minecraft triggers (such as item enchantment, block interactions, taming
animals, and earning stats) into an easy-to-use event bus. This allows developers to seamlessly listen to and handle
these actions across multi-loader environments (Forge, Fabric, and NeoForge) without needing to mixin to the triggers
themselves.

## For Developers

### Adding to your `build.gradle`

You can easily add Triggers to your development environment using the Modrinth Maven.

First, add the Modrinth maven to your `repositories` block:

```gradle
repositories {
    exclusiveContent {
        forRepository { 
            maven { 
                url = "https://api.modrinth.com/maven" 
            } 
        }
        filter { 
            includeGroup "maven.modrinth" 
        }
    }
}
````

Then, add the mod as a dependency in your `dependencies` block:

```gradle
dependencies {
    // Replace <version> with the target version of Triggers
    implementation "maven.modrinth:triggers:<version>" 
}
```

### Example Usage

Triggers uses a custom event bus that functions similarly to Forge's event bus. You can register listeners to
`Triggers.EVENTS` and listen for specific events.

```java
import com.evandev.triggers.Triggers;
import com.evandev.triggers.event.events.TriggerEntityEvent;
import com.evandev.triggers.event.events.TriggerPlayerEvent;

public class MyMod {
    public static void init() {
        // Listen for when a player enchants an item
        Triggers.EVENTS.addListener(TriggerPlayerEvent.Enchant.class, event -> {
            System.out.println(event.player.getName().getString() + " enchanted " + event.item);
        });

        // Listen for when an animal is tamed
        Triggers.EVENTS.addListener(TriggerEntityEvent.TameAnimal.class, event -> {
            if (event.causedByPlayer != null) {
                System.out.println("Animal tamed by: " + event.causedByPlayer.getName().getString());
            }
        });
    }
}
```

## License

[![Code license (MIT)](https://img.shields.io/badge/code%20license-MIT-green.svg?style=flat-square)](https://github.com/evanbones/Triggers/blob/1.20.1/LICENSE)

---

[![discord-plural](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/social/discord-plural_vector.svg)](https://discord.com/invite/JcGRdT6Pbx) [![github-plural](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/social/github-plural_vector.svg)](https://github.com/evanbones/Triggers)
