/*
 * This file is part of OneConfig.
 * OneConfig - Next Generation Config Library for Minecraft: Java Edition
 * Copyright (C) 2021~2024 Polyfrost.
 *   <https://polyfrost.org> <https://github.com/Polyfrost/>
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *   OneConfig is licensed under the terms of version 3 of the GNU Lesser
 * General Public License as published by the Free Software Foundation, AND
 * under the Additional Terms Applicable to OneConfig, as published by Polyfrost,
 * either version 1.0 of the Additional Terms, or (at your option) any later
 * version.
 *
 *   This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 * License.  If not, see <https://www.gnu.org/licenses/>. You should
 * have also received a copy of the Additional Terms Applicable
 * to OneConfig, as published by Polyfrost. If not, see
 * <https://polyfrost.org/legal/oneconfig/additional-terms>
 */

//#if FORGE==1
package org.polyfrost.oneconfig.internal.mixin;

import net.minecraftforge.fml.client.ClientModLoader;
import org.polyfrost.oneconfig.api.events.EventManager;
import org.polyfrost.oneconfig.api.events.event.InitializationEvent;
import org.polyfrost.oneconfig.internal.OneConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientModLoader.class, remap = false)
public class ClientModLoaderMixin {

    @Inject(method = "lambda$finishModLoading$9", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/GameSettings;loadOptions()V", remap = true), remap = false)
    private static void onFinishModLoading(CallbackInfo ci) {
        EventManager.INSTANCE.post(InitializationEvent.INSTANCE);
        OneConfig.INSTANCE.init();
    }
}
//#endif