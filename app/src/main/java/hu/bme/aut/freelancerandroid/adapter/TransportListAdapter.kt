package hu.bme.aut.freelancerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.model.Transfer


class TransportListAdapater(private val listener: TransportItemClickListener) : RecyclerView.Adapter<TransportListAdapater.TransportViewHolder>(){


    private val differCallback = object : DiffUtil.ItemCallback<Transfer>() {
        override fun areItemsTheSame(oldItem: Transfer, newItem: Transfer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transfer, newItem: Transfer): Boolean {
            return oldItem == newItem
        }
    }

    val transports = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.transport_row, parent, false)

        return TransportViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transports.currentList.size
    }

    interface TransportItemClickListener{
        fun onItemDelete(item: Transfer)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        val transport = transports.currentList[position]
        holder.item =transport
        holder.dateTextView.text = transport.date.toString()
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(transport) }
            }
        }

    }

    private var onItemClickListener: ((Transfer) -> Unit)? = null

    fun setOnItemClickListener(listener: (Transfer) -> Unit){
        onItemClickListener = listener
    }

    inner class TransportViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val dateTextView : TextView
        private val removeButton : ImageButton
        var item :Transfer? = null

        init{
            removeButton = itemView.findViewById(R.id.imremovetransport)
            dateTextView = itemView.findViewById(R.id.tvDate)
            removeButton.setOnClickListener(){ listener.onItemDelete(item!!)}
        }
    }
}