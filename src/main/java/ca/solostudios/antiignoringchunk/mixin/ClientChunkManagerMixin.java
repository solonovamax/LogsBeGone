package ca.solostudios.antiignoringchunk.mixin;

import net.minecraft.client.world.ClientChunkManager;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(ClientChunkManager.class)
public class ClientChunkManagerMixin {
    
    @Redirect(method = "loadChunkFromPacket",
              at = @At(value = "INVOKE",
                       target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"))
    public void ignoringChunkWarning(Logger logger, String message, Object p1, Object p2) {
        logger.debug(message, p1, p2); // fuck you mojang
    }
}
