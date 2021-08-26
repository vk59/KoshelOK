package com.tinkoffsirius.koshelok.ui.walletlist.adapters

import android.annotation.SuppressLint
import android.graphics.PointF
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemWalletBinding
import java.lang.Math.abs

class WalletRecyclerAdapter(
    private val onItemClick: (WalletItem) -> Unit,
    private val deleteItem: (WalletItem) -> Unit,
    private val editItem: (WalletItem) -> Unit,
    private val watchItem: () -> Unit
) : RecyclerView.Adapter<WalletViewHolder>() {

    private val diff = AsyncListDiffer(this, WalletDiffUtils())

    init {
        setHasStableIds(true)
    }

    fun setData(data: List<WalletItem>) {
        diff.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = WalletViewHolder(
            ItemWalletBinding.inflate(inflater, parent, false),
            deleteItem,
            editItem,
            watchItem
        )

        holder.itemView.setOnTouchListener(object : View.OnTouchListener {
            private var downClick: PointF = PointF(0f, 0f)

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        downClick = PointF(event.x, event.y)
                    }
                    MotionEvent.ACTION_UP -> {
                        if (isClick(PointF(event.x, event.y), downClick)
                        ) {
                            onItemClick(diff.currentList[holder.adapterPosition])
                        }
                    }
                }
                return false
            }
        })
        return holder
    }

    private fun isClick(upClick: PointF, downClick: PointF): Boolean {
        return (abs(upClick.x - downClick.x) < 10 && abs(upClick.y - downClick.y) < 10)
    }

    override fun onViewRecycled(holder: WalletViewHolder) {
        super.onViewRecycled(holder)
        holder.reset()
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val walletItem = diff.currentList[position]
        holder.bind(walletItem)
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_WALLET
    }

    override fun getItemCount(): Int = diff.currentList.size

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_WALLET = 1
    }
}
