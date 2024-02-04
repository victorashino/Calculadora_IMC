package com.example.calculadoraimc.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculadoraimc.R
import com.example.calculadoraimc.domain.IMC
import com.example.calculadoraimc.databinding.ActivityHistoryBinding
import com.example.calculadoraimc.databinding.ItemImcHistoryBinding

class HistoryActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context): Intent {
            return Intent(context, HistoryActivity::class.java)
        }
    }

    private val viewModel: HistoryViewModel by lazy {
        HistoryViewModel.create(application)
    }
    private val adapter: HistoryListAdapter by lazy {
        HistoryListAdapter(::deleteItem)
    }

    private lateinit var binding: ActivityHistoryBinding

    private lateinit var bindingItem: ItemImcHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        bindingItem = ItemImcHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rvHistory = binding.rvHistory
        rvHistory.adapter = adapter

        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rvHistory.layoutManager = layoutManager

        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        this@HistoryActivity.overridePendingTransition(
            R.anim.animate_slide_left_enter,
            R.anim.animate_fade_exit
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@HistoryActivity.overridePendingTransition(
            R.anim.animate_fade_enter,
            R.anim.animate_slide_out_right
        )
    }

    override fun onStart() {
        super.onStart()
        listFromDataBase()
    }

    private fun listFromDataBase() {
        val listObserver = Observer<List<IMC>> { listItems ->
            adapter.submitList(listItems)
        }
        viewModel.historyLiveData.observe(this, listObserver)
    }

    private fun deleteItem(id: Int) {
        viewModel.deleteItem(id)
    }
}