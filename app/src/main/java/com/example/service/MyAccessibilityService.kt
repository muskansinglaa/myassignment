package com.example.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class MyAccessibilityService:AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.packageName == "com.whatsapp" && event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Toast.makeText(this, "WhatsApp Launched.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInterrupt() {

    }
    override fun onServiceConnected() {
        super.onServiceConnected()
        // Your service connected logic here
    }
}