package com.aria.roomdatabasedemoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aria.roomdatabasedemoapplication.database.User

class UserAdapter(
    private val setOnClickedItem: (User) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {

    private val userList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_user, parent, false)

        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position], setOnClickedItem)
    }

    fun setList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
    }
}

class UserViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
    fun bind(user: User, setOnClickedItem: (User) -> Unit) {
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val tvEmail = view.findViewById<TextView>(R.id.tvMail)

        tvUsername.text = user.userName
        tvEmail.text = user.email

        // Process click item user
        view.setOnClickListener {
            setOnClickedItem(user)
        }
    }
}