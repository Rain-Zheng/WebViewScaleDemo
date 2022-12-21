package com.example.webviewscaleissue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.core.view.updateLayoutParams

class MainActivity : AppCompatActivity() {
    private lateinit var webView1: WebView
    private lateinit var webView2: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView1 = findViewById(R.id.webView1)
        webView2 = findViewById(R.id.webView2)
        webView1.settings.apply {
            setSupportZoom(true)
            builtInZoomControls = true
        }
        webView2.settings.apply {
            setSupportZoom(true)
            builtInZoomControls = true
        }
        webView1.loadUrl("file:///android_asset/plain_text.html")
        webView2.loadUrl("file:///android_asset/plain_text.html")

        val originalWidth = 600
        val originalHeight = 900
        val originalScale = 3
        webView1.webViewClient = object : WebViewClient() {
            override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
                super.onScaleChanged(view, oldScale, newScale)
                webView1.updateLayoutParams {
                    width = (originalWidth / originalScale * newScale).toInt()
                    height = (originalHeight / originalScale * newScale).toInt()
                }
                webView1.invalidate()
            }
        }

        webView2.webViewClient = object : WebViewClient() {
            override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
                super.onScaleChanged(view, oldScale, newScale)
                webView2.updateLayoutParams {
                    width = (originalWidth / originalScale * newScale).toInt()
                }
                webView2.invalidate()
            }
        }
    }
}