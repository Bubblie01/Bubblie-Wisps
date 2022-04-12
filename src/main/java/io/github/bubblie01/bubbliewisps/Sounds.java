package io.github.bubblie01.bubbliewisps;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sounds {
    public static final Identifier FLUTE_NOTE_1_ID = new Identifier(Main.MOD_ID, "flute_note_one");
    public static SoundEvent FLUTE_NOTE_1_EVENT = new SoundEvent(FLUTE_NOTE_1_ID);

    public static void registerSounds() {
        Registry.register(Registry.SOUND_EVENT, Main.MOD_ID, FLUTE_NOTE_1_EVENT);
    }

}
