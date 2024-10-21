package com.example.finale.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.finale.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    @OptIn(DelicateCoroutinesApi::class)

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: myAdapter
    private lateinit var clicklambdafunction: () -> Unit

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllObjects()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.entitiesState.collect { itemsInApiResponse ->
                    if (itemsInApiResponse.isNotEmpty()) {

                        clicklambdafunction= { Log.v("s4679530","Item Clicked")}

                        val itemList = mutableListOf<String>()
                        itemsInApiResponse.map { entity ->
                            itemList += ("Item: ${entity.itemName}\n" +
                                    "Designer: ${entity.designer}\n" +
                                    "Year Introduced: ${entity.yearIntroduced}\n" +
                                    "Category: ${entity.category}\n" +
                                    "Material: ${entity.material}")
                        }

                        recyclerView = view.findViewById(R.id.recyclerView)
                        adapter = myAdapter(itemList,clicklambdafunction)
                        recyclerView.adapter=adapter
                        adapter.updateData(itemList)
                    }
                }
            }
        }
    }
}