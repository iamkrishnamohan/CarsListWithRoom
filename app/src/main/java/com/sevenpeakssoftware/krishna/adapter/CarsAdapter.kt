package com.sevenpeakssoftware.krishna.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.sevenpeakssoftware.krishna.R
import com.sevenpeakssoftware.krishna.databinding.CarsRowLayoutBinding
import com.sevenpeakssoftware.krishna.response.CarsDetailsResponse
import com.sevenpeakssoftware.krishna.utils.Constants.BASE_URL
import com.sevenpeakssoftware.krishna.utils.convertDateToDifferentFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsAdapter @Inject constructor() : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    private lateinit var binding: CarsRowLayoutBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CarsRowLayoutBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int = position
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: CarsDetailsResponse.Content) {
            binding.apply {
                tvCarTitle.text = item.title
                tvCarDesc.text = item.ingress
                item.dateTime?.let {
                    val convertedDate = convertDateToDifferentFormat(it)
                    tvCarDateTime.text = convertedDate
                }

                val moviePosterURL = BASE_URL + item?.image
                imageView.load(moviePosterURL) {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                    scale(Scale.FILL)
                }

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((CarsDetailsResponse.Content) -> Unit)? = null


    private val differCallback = object : DiffUtil.ItemCallback<CarsDetailsResponse.Content>() {
        override fun areItemsTheSame(
            oldItem: CarsDetailsResponse.Content,
            newItem: CarsDetailsResponse.Content
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CarsDetailsResponse.Content,
            newItem: CarsDetailsResponse.Content
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
