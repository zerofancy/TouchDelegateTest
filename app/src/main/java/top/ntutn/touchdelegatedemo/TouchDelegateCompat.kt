package top.ntutn.touchdelegatedemo

import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View

class TouchDelegateCompat(val rect: Rect, val delegateView: View) : TouchDelegate(rect, delegateView)