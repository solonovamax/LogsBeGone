package ca.solostudios.logsbegone.mixin;

import net.minecraft.world.chunk.Chunk;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(Chunk.class)
public class ChunkMixin {
    
    @Redirect(method = "markBlockForPostProcessing(Lnet/minecraft/util/math/BlockPos;)V",
              at = @At(value = "INVOKE",
                       target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V"))
    public void ignoringChunkWarning(Logger logger, String message, Object p1) {
//        logger.debug(message, p1); // fuck you mojang
    }
}
