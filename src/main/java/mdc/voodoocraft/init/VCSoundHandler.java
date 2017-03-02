package mdc.voodoocraft.init;

import mdc.voodoocraft.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class VCSoundHandler {

    public static SoundEvent lightritual;
    public static SoundEvent rainritual;
    public static SoundEvent spiritguideritual;
    public static SoundEvent summonritual;
    public static SoundEvent templeritual;

    private static int size = 0;

    public static void init(){
        size = SoundEvent.REGISTRY.getKeys().size();
        lightritual = register("lightritual");
        rainritual = register("rainritual");
        spiritguideritual = register("spiritguideritual");
        summonritual = register("summonritual");
        templeritual = register("templeritual");
    }

    public static SoundEvent register(String name){
        ResourceLocation loc = new ResourceLocation(Reference.MODID, name);
        SoundEvent sEvent = new SoundEvent(loc);
        SoundEvent.REGISTRY.register(size, loc, sEvent);
        size++;

        return sEvent;
    }

}
