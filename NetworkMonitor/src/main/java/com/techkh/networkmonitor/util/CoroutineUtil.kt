package com.techkh.networkmonitor.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

    /**
     * @Author: SRENG Khorn
     * @Date: 01 May 2020
     *
     *  */

object CoroutineUtil {

    /**
     * ------------------------------------
     * Dispatchers.Main (Main Safety)
     * ------------------------------------
     * Main thread on Android, interact with the UI and perform light work
     * - Calling suspend functions
     * - Call UI functions
     * - Updating LiveData
     *
     * */
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    /**
     * --------------------------------------------------------
     * Dispatchers.IO (Network request or Disk Read/Write)
     * --------------------------------------------------------
     * Optimized for disk and network IO off the main thread
     * - Database*
     * - Reading/writing files
     * - Networking**
     *
     * */
    fun io(work: suspend () -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    /**
     * ------------------------------------------------
     * Dispatchers.Default (CPU Intensive Task)
     * ------------------------------------------------
     * Optimized for CPU intensive work off the main thread
     * - Sorting a list
     * - Parsing JSON
     * - DiffUtils
     */
    fun default(work: suspend () -> Unit) =
        CoroutineScope(Dispatchers.Default).launch {
            work()
        }


    /**
    * Room will provide main-safety automatically if you use suspend functions, RxJava, or LiveData.
    * Networking libraries such as Retrofit and Volley manage their own threads and do not require explicit main-safety in your code when used with Kotlin coroutines.
    *
    *  */
}