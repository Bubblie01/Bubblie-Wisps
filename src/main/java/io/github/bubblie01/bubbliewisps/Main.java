package io.github.bubblie01.bubbliewisps;

import io.github.bubblie01.bubbliewisps.items.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String MOD_ID = "bubbliewisp";
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		FakeBlockEntity.registerFakeBlockEntityAttributes();
		ItemRegistry.registerItems();
		Sounds.registerSounds();
		LOGGER.info("Hello Fabric world!");
	}
}
