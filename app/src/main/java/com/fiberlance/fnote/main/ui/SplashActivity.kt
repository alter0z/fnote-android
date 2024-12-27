package com.fiberlance.fnote.main.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fiberlance.fnote.databinding.ActivitySplashBinding
import com.fiberlance.fnote.notes.presentation.ui.views.NotesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private var _binding: ActivitySplashBinding? = null
    private val activityScope = CoroutineScope(Dispatchers.Main)
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.main?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        activityScope.launch {
            delay(1000)
            startActivity(Intent(this@SplashActivity, NotesActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}