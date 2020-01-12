package com.jamesscn.hyrule;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.*;

@Mod(HyruleMod.M_ID)
public class HyruleMod {
    public static final String M_ID = "hyrule";
    public static final Logger LOGGER = LogManager.getLogger(M_ID);

    public HyruleMod() {
        LOGGER.debug("It's dangerous to go alone! Take this.");
    }
}
