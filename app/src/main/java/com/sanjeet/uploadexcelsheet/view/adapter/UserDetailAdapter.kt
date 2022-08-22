package com.sanjeet.uploadexcelsheet.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjeet.uploadexcelsheet.R
import com.sanjeet.uploadexcelsheet.database.Student
import com.sanjeet.uploadexcelsheet.databinding.UserDetailItemBinding

class UserDetailAdapter : RecyclerView.Adapter<UserDetailAdapter.MyViewHolder>() {

    private var listener: ((Student) -> Unit)? = null
    var list = mutableListOf<Student>()
    var userType: String? = null

    fun setContentList(list: MutableList<Student>, userType: String) {
        this.list = list
        this.userType = userType
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: UserDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDetailAdapter.MyViewHolder {
        val binding =
            UserDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l: (Student) -> Unit) {
        listener = l
    }

    override fun onBindViewHolder(holder: UserDetailAdapter.MyViewHolder, position: Int) {

        holder.binding.student = this.list[position]

        holder.binding.tvStatus.isEnabled = false
        if (userType.equals("user")) {
            holder.binding.tvStatus.isEnabled = true
            holder.binding.tvStatus.setBackgroundColor(
                holder.binding.tvStatus.getResources().getColor(R.color.bg_color)
            )
        }

        holder.binding.tvStatus.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}