package top.ntutn.touchdelegatedemo

import android.graphics.Rect
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi

class TouchDelegateAdapter(private val parent: ViewGroup): TouchDelegateCompat(Rect(), parent) {
    private val delegateSet = mutableSetOf<TouchDelegateCompat>()

    fun addDelegate(delegate: TouchDelegateCompat) {
        delegate.let {
            if (it is TouchDelegateAdapter) {
                it.delegateSet
            } else {
                listOf(it)
            }
        }.forEach {
            delegateSet.add(it)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var res = false
        val x = event.x
        val y = event.y

        delegateSet.forEach {
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

        delegateSet.forEach {
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
