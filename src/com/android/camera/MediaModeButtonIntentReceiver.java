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
import android.content.ComponentName; 
import android.app.ActivityManager;
import android.util.Log;
import java.util.List; 

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

public class MediaModeButtonIntentReceiver extends BroadcastReceiver {


	private final String cameraApp = "com.android.camera.Camera";
	private final String videoCameraApp = "com.android.camera.VideoCamera"; 
	private final String galleryApp =  "com.cooliris.media.Gallery"; 

    private void startGallery(Context paramContext)
	{
		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setComponent(ComponentName.unflattenFromString("com.cooliris.media/.Gallery"));
	    i.addCategory(Intent.CATEGORY_LAUNCHER);
		paramContext.startActivity(i);
	}


    private void startCamera(Context paramContext)
	{
		Intent i = new Intent(Intent.ACTION_MAIN);
		i.setClass(paramContext, Camera.class);
		i.addCategory(Intent.CATEGORY_LAUNCHER);
	        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        paramContext.startActivity(i);
	}

    private void startVideoCamera(Context paramContext)
	{
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.setClass(paramContext, VideoCamera.class);
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                paramContext.startActivity(i);
	}
    private String getCurrentTopActivity(Context context) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
        return ar.topActivity.getClassName().toString();
    }

		
    @Override
    public void onReceive(Context context, Intent intent) {
//	Log.i("MediaButtonReceiver","event recieved");
        // Try to get the camera hardware
        //CameraHolder holder = CameraHolder.instance();
        //ComboPreferences pref = new ComboPreferences(context);
        //int cameraId = CameraSettings.readPreferredCameraId(pref);
        //if (holder.tryOpen(cameraId) == null) return;

        // We are going to launch the camera, so hold the camera for later use
        //holder.keep();
        //holder.release();
        //Intent i = new Intent(Intent.ACTION_MAIN);
        //i.setClass(context, Camera.class);
        //i.addCategory(Intent.CATEGORY_LAUNCHER);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
         //       | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //context.startActivity(i);
	//startGallery(context);
	
//	List activies = (ActivityManager) context.getSystemService("activity").getRunningTasks(10); // i need more study about
//	if (activies.get(0).equals(appName)) 
//		{
//			// TODO: Check camera app, and set camera or videocamera.
//		}
//	startGallery(context);
	String activity = getCurrentTopActivity(context);
	Log.i("MediaModeButtonReceiver", "Current activity: " + activity);
	if (activity.equals(cameraApp))
		{
			startVideoCamera(context);
		}
	else if (activity.equals(videoCameraApp))
		{
			startGallery(context);
		}
	else if (activity.equals(galleryApp)) {
			startCamera(context);
		}
	else startGallery(context);

    }
}
