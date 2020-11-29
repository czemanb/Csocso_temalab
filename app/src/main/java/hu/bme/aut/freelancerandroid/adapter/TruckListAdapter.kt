package hu.bme.aut.freelancerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.TruckListAdapter.TruckViewHolder
import hu.bme.aut.freelancerandroid.repository.dto.VehicleDto
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.Vehicle

class TruckListAdapter(private val listener: TruckListAdapter.TruckItemClickListener) : RecyclerView.Adapter<TruckViewHolder>(){


    private val differCallback = object : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }
    }

    val trucks = AsyncListDiffer(this, differCallback)
//      val trucks : MutableList<Vehicle> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.truck_row, parent, false)

        return TruckViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return trucks.currentList.size
        //return trucks.size
    }

    override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
        val truck = trucks.currentList[position]
        //val truck = trucks[position]

        holder.truckNameTextView.text = truck.name.toString()
    }

    interface TruckItemClickListener{
        fun onItemChanged(item: Vehicle)
    }

    inner class TruckViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val truckNameTextView : TextView

        init{
            truckNameTextView = itemView.findViewById(R.id.tvTruckName)
        }
    }
}