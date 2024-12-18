/*
 * Copyright (c) 2024 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file ClientConnectionMixin.java is part of LogsBeGone
 * Last modified on 18-12-2024 05:30 p.m.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * LOGSBEGONE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ca.solostudios.logsbegone.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.netty.channel.unix.Errors;
import io.netty.handler.timeout.ReadTimeoutException;
import net.minecraft.network.ClientConnection;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.net.SocketException;
import java.nio.channels.ClosedChannelException;

@Mixin(value = ClientConnection.class, priority = 1500)
public class ClientConnectionMixin {
    @TargetHandler(
            mixin = "io.github.fabricators_of_create.porting_lib.mixin.common.ConnectionMixin",
            name = "on_exceptionCaught",
            prefix = "handler"
    )
    @WrapOperation(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V",
                    remap = false
            ),
            require = 0
    )
    @SuppressWarnings({"InvalidMemberReference", "MixinAnnotationTarget", "UnresolvedMixinReference"})
    private void suppressExceptionOccurredInNettyPipeline(Logger logger, String message, Throwable throwable, Operation<Void> original) {
        if (throwable instanceof Errors.NativeIoException exception) {
            if (exception.expectedErr() == Errors.ERROR_ECONNREFUSED_NEGATIVE ||
                exception.expectedErr() == Errors.ERRNO_ECONNRESET_NEGATIVE)
                return;
        }

        if (throwable instanceof ClosedChannelException)
            return;
        else if (throwable instanceof ReadTimeoutException)
            return;
        else if (throwable instanceof SocketException)
            return;

        original.call(logger, message, throwable);
    }
}
