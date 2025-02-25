package top.ntutn.touchdelegatedemo

import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import top.ntutn.touchdelegatedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bind()
    }

    private fun bind() {
        binding.demoButton.setOnClickListener {
            Toast.makeText(this, "点击了测试按钮", Toast.LENGTH_SHORT).show()
        }
        binding.root.post(::expandHitArea)
        binding.showOrHideButton.setOnClickListener {
            binding.demoButton.visibility = if (binding.demoButton.visibility == View.VISIBLE) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

    private fun expandHitArea() {
        val expandedHitRect = Rect()
        binding.demoButton.getHitRect(expandedHitRect)
        expandedHitRect.apply {
            left -= 24
            top -= 24
            right += 24
            bottom += 24
        }
//        binding.demoButtonContainer.touchDelegate = TouchDelegateAdapter(binding.demoButtonContainer).also {
//            it.addDelegate(TouchDelegateCompat(expandedHitRect, binding.demoButton))
//        }
        binding.demoButtonContainer.touchDelegate = TouchDelegateCompat(expandedHitRect, binding.demoButton)
    }
}