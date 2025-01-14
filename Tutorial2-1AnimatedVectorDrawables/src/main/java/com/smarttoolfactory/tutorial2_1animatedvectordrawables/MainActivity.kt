package com.smarttoolfactory.tutorial2_1animatedvectordrawables

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarttoolfactory.tutorial2_1animatedvectordrawables.adapter.BaseAdapter
import com.smarttoolfactory.tutorial2_1animatedvectordrawables.adapter.ChapterSelectionAdapter
import com.smarttoolfactory.tutorial2_1animatedvectordrawables.adapter.model.ActivityClassModel
import com.smarttoolfactory.tutorial2_1animatedvectordrawables.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity(), BaseAdapter.OnRecyclerViewItemClickListener {

    private val activityClassModels = ArrayList<ActivityClassModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "MainActivity"

        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        addChapters()

        val recyclerView = activityMainBinding.recyclerView

        recyclerView.apply {

            // Use fixed size for performance
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@MainActivity)

            // Add vertical divider
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

            // Add Adapter
            recyclerView.adapter = ChapterSelectionAdapter(activityClassModels).apply {
                setOnItemClickListener(this@MainActivity)
            }
        }
    }

    private fun addChapters() {

        // Add Activities to list to be displayed on RecyclerView
        activityClassModels.add(
            ActivityClassModel(
                Activity1_1Basics::class.java,
                getString(R.string.activity1_1)
            )
        )

        activityClassModels.add(
            ActivityClassModel(
                Activity1_2StateChange::class.java,
                getString(R.string.activity1_2)
            )
        )

        activityClassModels.add(
            ActivityClassModel(
                Activity1_3BottomNavIcons::class.java,
                getString(R.string.activity1_3)
            )
        )
    }

    @Override
    override fun onItemClicked(view: View, position: Int) {
        Intent(this@MainActivity, activityClassModels[position].clazz).also {
            startActivity(it)
        }
    }
}