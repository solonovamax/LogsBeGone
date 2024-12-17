/*
 * Copyright (c) 2024 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file AnimationControllerMixin.java is part of LogsBeGone
 * Last modified on 16-12-2024 07:59 p.m.
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

package ca.solostudios.logsbegone.mixin.geckolib3;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import software.bernie.geckolib3.core.controller.AnimationController;

import java.io.PrintStream;

@Mixin(AnimationController.class)
public class AnimationControllerMixin {
    @Redirect(
            method = "lambda$setAnimation$0",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/io/PrintStream;printf(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;"
            ),
            require = 0,
            remap = false
    )
    public PrintStream silenceCouldNotLoadAnimation(PrintStream instance, String format, Object[] args) {
        // return instance.printf(format, args);
        return instance;
    }
}
