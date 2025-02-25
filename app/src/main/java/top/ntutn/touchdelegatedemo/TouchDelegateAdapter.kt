package top.ntutn.touchdelegatedemo

import android.graphics.Rect
import android.os.Build
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi

class TouchDelegateAdapter(private val parent: ViewGroup): TouchDelegate(Rect(), parent) {
    private val delegateList = mutableListOf<TouchDelegateCompat>()

    fun addDelegate(delegate: TouchDelegateCompat) {
        delegateList.add(delegate)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var res = false
        val x = event.x
        val y = event.y

        delegateList.forEach {
            val dView = it.delegateView
            if (dView.parent != parent || dView.visibility != View.VISIBLE) {
                // 该View已经被移除或不可见
                return@forEach
            }

            event.setLocation(x, y)
            res = it.onTouchEvent(event) || res
        }
        return res
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onTouchExplorationHoverEvent(event: MotionEvent): Boolean {
        var res = false
        val x = event.x
        val y = event.y

        delegateList.forEach {
            val dView = it.delegateView
            if (dView.parent != parent || dView.visibility != View.VISIBLE) {
                // 该View已经被移除
                return@forEach
            }

            event.setLocation(x, y)
            res = it.onTouchExplorationHoverEvent(event) || res
        }
        return res
    }
}
