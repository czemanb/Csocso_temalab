package hu.bme.aut.freelancerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.TruckListAdapter.TruckViewHolder
import hu.bme.aut.freelancerandroid.model.Transfer
import hu.bme.aut.freelancerandroid.model.Vehicle

class TruckListAdapter(private val listener: TruckListAdapter.TruckItemClickListener) : RecyclerView.Adapter<TruckViewHolder>(){

    private val trucks  = mutableListOf<Vehicle>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.truck_row, parent, false)

        return TruckViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return trucks.size
    }

    override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
        val truck = trucks[position]

        holder.truckNameTextView.text = truck.name.toString()
    }

    fun addTruck(truck: Vehicle) {
        trucks.add(truck)
        notifyItemInserted(trucks.size - 1)
    }

    fun update(vehicles: List<Vehicle>) {
        trucks.clear()
        trucks.addAll(vehicles)
        notifyDataSetChanged()
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