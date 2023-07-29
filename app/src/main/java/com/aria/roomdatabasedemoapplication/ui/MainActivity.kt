package com.aria.roomdatabasedemoapplication.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aria.roomdatabasedemoapplication.viewmodel.MainViewModel
import com.aria.roomdatabasedemoapplication.viewmodel.MainViewModelFactory
import com.aria.roomdatabasedemoapplication.R
import com.aria.roomdatabasedemoapplication.viewmodel.UserAdapter
import com.aria.roomdatabasedemoapplication.model.User
import com.aria.roomdatabasedemoapplication.data.UserDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button
    private lateinit var rvUsers: RecyclerView

    private lateinit var viewModel: MainViewModel
    private lateinit var userAdapter: UserAdapter

    private lateinit var selectedUser: User
    private var isItemUserClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mapping xml
        username = findViewById(R.id.etName)
        email = findViewById(R.id.etEmail)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        rvUsers = findViewById(R.id.rvUserList)

        // Init Dao and view model
        val dao = UserDatabase.getInstance(application).userDao()
        val factory = MainViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        processEvent()
        initRecyclerView()
    }

    private fun saveUserData() {
        viewModel.insertUser(User(0, username.text.toString(), email.text.toString()))
    }

    private fun updateUserData() {
        viewModel.updateUser(User(selectedUser.id, username.text.toString(), email.text.toString()))

        // Set item selected is back
        btnSave.text = resources.getString(R.string.save)
        btnClear.text = resources.getString(R.string.clear)
        isItemUserClicked = false
    }

    private fun deleteUserData() {
        viewModel.deleteUser(User(selectedUser.id, username.text.toString(), email.text.toString()))

        // Set item selected is back
        btnSave.text = resources.getString(R.string.save)
        btnClear.text = resources.getString(R.string.clear)
        isItemUserClicked = false
    }

    private fun clearInput() {
        username.setText("")
        email.setText("")
    }

    // [TODO] Handler validate form
    private fun checkValidate(): Boolean {
        if (username.text.isEmpty() || email.text.isEmpty()) {
            return true
        }
        return false
    }

    private fun processEvent() {
        btnSave.setOnClickListener {
            if (!checkValidate()) {
                if (isItemUserClicked) {
                    updateUserData()
                    clearInput()
                } else {
                    saveUserData()
                    clearInput()
                }
            } else {
                Toast.makeText(this, "Form invalid!", Toast.LENGTH_SHORT).show()
            }
        }
        btnClear.setOnClickListener {
            if (!checkValidate()) {
                if (isItemUserClicked) {
                    deleteUserData()
                    clearInput()
                } else {
                    clearInput()
                }
            } else {
                Toast.makeText(this, "Form invalid!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter { selectedUserItem: User ->
            onClickItemUser(selectedUserItem)
        }
        rvUsers.adapter = userAdapter

        displayUsers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayUsers() {
        viewModel.users.observe(this) {
            userAdapter.setList(it)
            userAdapter.notifyDataSetChanged()
        }
    }

    private fun onClickItemUser(user: User) {
        // Get item user, change button text and listen click item
        selectedUser = user
        btnSave.text = resources.getString(R.string.update)
        btnClear.text = resources.getString(R.string.delete)
        isItemUserClicked = true

        // Set username and email for edittext
        username.setText(user.userName)
        email.setText(user.email)
    }
}