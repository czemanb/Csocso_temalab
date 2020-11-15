package hu.bme.aut.freelancerandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.model.Transfer
import kotlinx.android.synthetic.main.fragment_dialog_add_package.view.*
import kotlinx.android.synthetic.main.package_row.view.*

class TransportListAdapater(private val listener: TransportItemClickListener) : RecyclerView.Adapter<TransportListAdapater.TransportViewHolder>(){

    private val transports  = mutableListOf<Transfer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.transport_row, parent, false)

        return TransportViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transports.size
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        val transport = transports[position]

        holder.nameTextView.text = transport.id.toString()
    }

    fun addTransport(transport: Transfer) {
        transports.add(transport)
        notifyItemInserted(transports.size - 1)
    }

    fun update(transfers: List<Transfer>) {
        transports.clear()
        transports.addAll(transfers)
        notifyDataSetChanged()
    }

    interface TransportItemClickListener{
        fun onItemChanged(item: Transfer)
    }

    inner class TransportViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTextView : TextView

        init{
            nameTextView = itemView.findViewById(R.id.textView2)

        }
    }
}