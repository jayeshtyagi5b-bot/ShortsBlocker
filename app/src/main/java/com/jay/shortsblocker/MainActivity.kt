package com.jay.shortsblocker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(64, 64, 64, 64)
        }
        val infoText = TextView(this).apply {
            text = "Shorts Blocker\n\nTurn on \"Shorts Blocker\" in Accessibility settings (under Installed/Downloaded apps). " +
                "After that, YouTube Shorts will close automatically."
            textSize = 16f
            gravity = Gravity.CENTER
        }
        val openBtn = Button(this).apply {
            text = "Open Accessibility Settings"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }
        layout.addView(infoText)
        layout.addView(openBtn)
        setContentView(layout)
    }
}
