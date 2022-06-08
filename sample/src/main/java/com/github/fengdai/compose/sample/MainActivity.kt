package com.github.fengdai.compose.sample

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val content = remember {
                movableContentOf { isLandscape: Boolean ->
                    AndroidView(factory = { context ->
                        Log.d("AndroidView", "create view")
                        TextView(context).apply {
                            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                        }
                    }) {
                        it.text = if (isLandscape) "Landscape" else "Portrait"
                    }
                }
            }
            val isLandscape =
                LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
            BoxWithConstraints {
                if (!isLandscape) {
                    content(false)
                }
            }
            if (isLandscape) {
                content(true)
            }
        }
    }
}
