package com.chingyi.caruitestapp.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Transformation
import androidx.recyclerview.widget.RecyclerView
import com.chingyi.caruitestapp.R


class AnimationAgent {
    fun runLayoutAnimation(recyclerView :RecyclerView,run :Boolean) {
        if(run) {
            val context = recyclerView.context
            val controller = AnimationUtils.loadLayoutAnimation(context , R.anim.layout_animation_bottom_up)
            recyclerView.layoutAnimation = controller
            recyclerView.adapter !!.notifyDataSetChanged()
            recyclerView.scheduleLayoutAnimation()
        }
    }

    fun shake(holder : View, goesDown: Boolean) {
        val animatorSet = AnimatorSet()
        val animatorTranslateY: ObjectAnimator = ObjectAnimator.ofFloat(
            holder,
            "translationY",
            if(goesDown) 20f else -20f,
            0f
        )

        animatorTranslateY.duration = 1000
        val animatorTranslateX = ObjectAnimator.ofFloat(
            holder,
            "translationX",
            -15f,
            15f,
            -10f,
            10f,
            -5f,
            5f,
            -2f,
            2f,
            0f
        )
        animatorTranslateX.duration = 2000

        animatorSet.playTogether(animatorTranslateX, animatorTranslateY)
        animatorSet.start()
    }

    fun expand(v: ViewGroup?, duration: Int) {
        slide(v!!, duration, true)
    }
    fun collapse(v: ViewGroup?, duration: Int) {
        slide(v!!, duration, false)
    }
    private fun slide(v: ViewGroup, duration: Int, expand: Boolean) {
        try {
            //onmeasure method is protected
            val m = v.javaClass.getDeclaredMethod(
                "onMeasure",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            m.isAccessible = true
            m.invoke(
                v,
                View.MeasureSpec.makeMeasureSpec(
                    (v.parent as View).measuredWidth,
                    View.MeasureSpec.AT_MOST
                ),
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                )
            )
        } catch (e: Exception) {
            Log.e("slideAnimation", e.message, e)
        }
        val initialHeight = v.measuredHeight
        if (expand) {
            v.layoutParams.height = 0
        } else {
            v.layoutParams.height = initialHeight
        }
        v.visibility = View.VISIBLE
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation
            ) {
                var newHeight = 0
                newHeight = if (expand) {
                    (initialHeight * interpolatedTime).toInt()
                } else {
                    (initialHeight * (1 - interpolatedTime)).toInt()
                }
                v.layoutParams.height = newHeight
                v.requestLayout()
                if (interpolatedTime == 1f && !expand) {
                    v.visibility = View.GONE
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = duration.toLong()
        v.startAnimation(a)
    }

    fun fade(view :View){
        view.alpha = 0.0f
        val shortAnimationDuration = 1000
        view.animate()
            //.translationY(0f)
            .alpha(1.0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    view.visibility=View.VISIBLE
                }
            })
    }

}