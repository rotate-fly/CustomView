package layout

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import fly.rotate.com.customview.R

/**
 * @author hzs
 * date: 2019/2/22
 */
class SelfViewAnimatorExample01 : AppCompatActivity() {

    var mIsMenuOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_example01)
        initView()

    }

    private fun initView() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val menu = this.findViewById<Button>(R.id.menu)
        val item1 = this.findViewById<Button>(R.id.item1)
        val item2 = this.findViewById<Button>(R.id.item2)
        val item3 = this.findViewById<Button>(R.id.item3)
        val item4 = this.findViewById<Button>(R.id.item4)
        val item5 = this.findViewById<Button>(R.id.item5)
        item1.setOnClickListener {
            Toast.makeText(this, "01", Toast.LENGTH_SHORT).show()
        }
        item2.setOnClickListener {
            Toast.makeText(this, "02", Toast.LENGTH_SHORT).show()
        }
        item3.setOnClickListener {
            Toast.makeText(this, "03", Toast.LENGTH_SHORT).show()
        }
        item4.setOnClickListener {
            Toast.makeText(this, "04", Toast.LENGTH_SHORT).show()
        }
        item5.setOnClickListener {
            Toast.makeText(this, "05", Toast.LENGTH_SHORT).show()
        }

        menu.setOnClickListener {
            if (!mIsMenuOpen) {
                mIsMenuOpen = true
                doAnimateOpen(item1, 0, 5, 300)
                doAnimateOpen(item2, 1, 5, 300)
                doAnimateOpen(item3, 2, 5, 300)
                doAnimateOpen(item4, 3, 5, 300)
                doAnimateOpen(item5, 4, 5, 300)
            } else {
                mIsMenuOpen = false
                doAnimateClose(item1, 0, 5, 300)
                doAnimateClose(item2, 1, 5, 300)
                doAnimateClose(item3, 2, 5, 300)
                doAnimateClose(item4, 3, 5, 300)
                doAnimateClose(item5, 4, 5, 300)
            }
        }

    }

    private fun doAnimateClose(view: Button, index: Int, total: Int, radius: Int) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        val degree = Math.PI * index / ((total - 1) * 2)
        val translationX: Float = (-(radius * Math.sin(degree))).toFloat()
        val translationY: Float = (-(radius * Math.cos(degree))).toFloat()
        val set = AnimatorSet()
        //包含平移、缩放和透明度动画
        /**
         * 此处修改为0.01是应为，收起动画结束后再次点击是，会Toast 04
         * 相应了item4控件的点击事件，即在布局FramentLayout中最上面的一个布局（此时，item4控件是FramentLayout最后的一个子布局）
         * */
//        set.playTogether(
//                ObjectAnimator.ofFloat(view, "translationX", translationX, 0f),
//                ObjectAnimator.ofFloat(view, "translationY", translationY, 0f),
//                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.01f),
//                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.01f),
//                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f))
//        set.setDuration(1 * 500).start()

        /**
         * 此处修改为0.01是应为，收起动画结束后再次点击是，会Toast 04
         * 相应了item4控件的点击事件，即在布局FramentLayout中最上面的一个布局（此时，item4控件是FramentLayout最后的一个子布局）
         * 当把menu控件置于FragmentLayout布局的最后一个子布局时没有这个问题
         * */
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0f),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0f),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.01f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.01f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f))
        set.setDuration(1 * 500).start()

    }

    private fun doAnimateOpen(view: Button, index: Int, total: Int, radius: Int) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        val degree = Math.toRadians(90.0) / (total - 1) * index
        val translationX: Float = (-(radius * Math.sin(degree))).toFloat()
        val translationY: Float = (-(radius * Math.cos(degree))).toFloat()

        val set = AnimatorSet()
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0f, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0f, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f))
        //动画周期为500ms
        set.setDuration(1 * 500).start()


    }
}