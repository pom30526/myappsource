package com.example.lsm.pichingreport.manager;

import android.hardware.Camera;

/**
 * Created by lsm on 2015-03-03.
 */
public class CameraManager {

    public static Camera getCameraInstance(Camera c) {
        c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
        }
        return c;
    }

    public static void releaseCamera(Camera c) {
        if (c != null) {
            c.release();
            c = null;
        }
    }

}
