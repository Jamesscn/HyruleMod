package com.jamesscn.hyrule;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.*;

@Mod(HyruleMod.M_ID)
public class HyruleMod {
    public static final String M_ID = "hyrule";
    public static final Logger LOGGER = LogManager.getLogger(M_ID);

    public HyruleMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ForgeEventSubscriber::onCommonSetup);
        LOGGER.debug("It's dangerous to go alone! Take this.");
    }
    public static void print(Object msg) {
        Minecraft mc = Minecraft.getInstance();
        mc.player.sendChatMessage(msg.toString());
    }
}
