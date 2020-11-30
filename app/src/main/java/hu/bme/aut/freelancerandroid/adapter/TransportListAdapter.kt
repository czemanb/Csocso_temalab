package hu.bme.aut.freelancerandroid.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.repository.model.Package
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import kotlinx.android.synthetic.main.fragment_dialog_add_package.view.*
import kotlinx.android.synthetic.main.package_row.view.*

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

    //val transports: MutableList<Transfer> = mutableListOf<Transfer>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.transport_row, parent, false)

        return TransportViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transports.currentList.size
        //return transports.size

    }

    interface TransportItemClickListener{
        fun onItemDelete(item: Transfer)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        val transport = transports.currentList[position]
        //val transport = transports[position]
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

//    fun addTransport(transport: Transfer) {
//        transports.add(transport)
//        notifyItemInserted(transports.size - 1)
//    }
//
//    fun update(transfers: List<Transfer>) {
//        transports.clear()
//        transports.addAll(transfers)
//        notifyDataSetChanged()
//    }

    inner class TransportViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val dateTextView : TextView
        val removeButton : ImageButton
        var item :Transfer? = null

        init{
            removeButton = itemView.findViewById(R.id.imremovetransport)
            dateTextView = itemView.findViewById(R.id.tvDate)
            removeButton.setOnClickListener(){ listener.onItemDelete(item!!)}
        }
    }
}