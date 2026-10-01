package com.jay.shortsblocker

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class ShortsBlockerService : AccessibilityService() {

    private var lastBlock = 0L

    // View IDs that exist only on the Shorts player screen inside the YouTube app.
    // If a YouTube update renames them, add the new IDs here.
    private val shortsIds = listOf(
        "com.google.android.youtube:id/reel_recycler",
        "com.google.android.youtube:id/reel_player_page_container",
        "com.google.android.youtube:id/reel_watch_fragment_root"
    )

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.packageName != "com.google.android.youtube") return

        val now = System.currentTimeMillis()
        if (now - lastBlock < 800) return // avoid repeated back presses

        val root: AccessibilityNodeInfo = rootInActiveWindow ?: return
        val inShorts = shortsIds.any { root.findAccessibilityNodeInfosByViewId(it).isNotEmpty() }

        if (inShorts) {
            lastBlock = now
            performGlobalAction(GLOBAL_ACTION_BACK)
        }
    }

    override fun onInterrupt() {}
}
