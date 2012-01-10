/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.camera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * {@code MediaModeButtonIntentReceiver} is invoked when the media mode button is
 * pressed.
 *
 * It is declared in {@code AndroidManifest.xml} to receive the
 * {@code android.intent.action.MEDIA_MODE_BUTTON} intent.
 *
 * After making sure we can use the camera hardware, it starts the camera
 * activity.
 * In next version i will add switcher. 
 */

public class MediaModeIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Try to get the camera hardware
        CameraHolder holder = CameraHolder.instance();
        ComboPreferences pref = new ComboPreferences(context);
        int cameraId = CameraSettings.readPreferredCameraId(pref);
        if (holder.tryOpen(cameraId) == null) return;

        // We are going to launch the camera, so hold the camera for later use
        holder.keep();
        holder.release();
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setClass(context, Camera.class);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }
}
