package com.tinkoffsirius.koshelok.ui.walletlist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tinkoffsirius.koshelok.databinding.ItemWalletBinding
import java.util.*

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
            private val MAX_CLICK_DURATION = 70
            private var startClickTime: Long = 0

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startClickTime = Calendar.getInstance().timeInMillis
                    }
                    MotionEvent.ACTION_UP -> {
                        val clickDuration: Long =
                            Calendar.getInstance().timeInMillis - startClickTime
                        if (clickDuration < MAX_CLICK_DURATION) {
                            onItemClick(diff.currentList[holder.adapterPosition])
                        }
                    }
                }
                return false
            }
        })
        return holder
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
