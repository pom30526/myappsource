package com.example.lsm.pichingreport.manager;

import android.hardware.Camera;
import android.media.MediaRecorder;

/**
 * Created by lsm on 2015-03-03.
 */
public class MediaManager {

    public static void releaseMediaRecorder(MediaRecorder mMediaRecorder,
                                            Camera c) {
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            c.lock();
        }
    }

}
