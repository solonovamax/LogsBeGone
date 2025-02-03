/*
 * Copyright (c) 2024-2025 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file MainConfigMixin.java is part of LogsBeGone
 * Last modified on 03-02-2025 02:38 p.m.
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

package ca.solostudios.logsbegone.mixin.bclib;

import org.betterx.bclib.config.MainConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MainConfig.class)
public class MainConfigMixin {
    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/betterx/bclib/config/NamedPathConfig$ConfigToken;Boolean(ZLjava/lang/String;Ljava/lang/String;)Lorg/betterx/bclib/config/NamedPathConfig$ConfigToken;",
                    remap = false,
                    ordinal = 1
            ),
            index = 0,
            remap = false
    )
    private static boolean disableVerboseLogging(boolean defaultValue) {
        return false;
    }
}
