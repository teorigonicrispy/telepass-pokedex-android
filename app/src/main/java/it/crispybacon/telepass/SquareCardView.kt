package it.crispybacon.telepass

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView

class SquareCardView(context: Context?, attrs: AttributeSet?) :
    MaterialCardView(context!!, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }
}
