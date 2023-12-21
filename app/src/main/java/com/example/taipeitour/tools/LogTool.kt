package com.example.taipeitour.tools

class LogTool {
    companion object {
        const val TAG = "TaipeiTour"

        fun d(msg: String) {
            android.util.Log.d(TAG, msg)
        }

        fun i(msg: String) {
            android.util.Log.i(TAG, msg)
        }

        fun w(msg: String) {
            android.util.Log.w(TAG, msg)
        }

        fun e(msg: String) {
            android.util.Log.e(TAG, msg)
        }
    }
}