package com.example.finale.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.finale.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(), View.OnClickListener {

    @OptIn(DelicateCoroutinesApi::class)

    private val viewModel: DetailsViewModel by viewModels()

    private var navc: NavController?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("s4679530", "DetailsFragment Created")

        navc = Navigation.findNavController(view)

        view.findViewById<ImageButton>(R.id.backImageButton)?.setOnClickListener(this)

        // Retrieve the passed data from the arguments
        val selectedItemIndex = arguments?.getInt("SelectedItemIndex")
        Log.v("s4679530", "Clicked item text: $selectedItemIndex")

        viewModel.getAllObjects()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.entitiesState.collect { itemsInApiResponse ->
                    if (itemsInApiResponse.isNotEmpty()) {
                        // Log the index found
                        Log.v("s4679530", "Index of clicked item: $selectedItemIndex")

                        // Set the Title for the Details Page based on the selected exercise
                        view.findViewById<TextView>(R.id.detailsTextViewTitle).setText(itemsInApiResponse[selectedItemIndex!!].itemName)

                        // Set text of each of the TextViews that are used to display the detailed information for the selected items
                        view.findViewById<TextView>(R.id.itemNameTextView).setText(itemsInApiResponse[selectedItemIndex].itemName)
                        view.findViewById<TextView>(R.id.designerTextView).setText(itemsInApiResponse[selectedItemIndex].designer)
                        view.findViewById<TextView>(R.id.yearIntroducedTextView).setText(itemsInApiResponse[selectedItemIndex].yearIntroduced)
                        view.findViewById<TextView>(R.id.categoryTextView).setText(itemsInApiResponse[selectedItemIndex].category)
                        view.findViewById<TextView>(R.id.materialTextView).setText(itemsInApiResponse[selectedItemIndex].material)
                        view.findViewById<TextView>(R.id.descriptionTextView).setText(itemsInApiResponse[selectedItemIndex].description)
                    }
                }
            }
        }
    }

    // When the back button is pressed transition from details fragment to dashboard fragment
    override fun onClick(v: View?) {
        Log.v("s4679530", "Back button pressed")
        navc?.navigate(R.id.action_detailsFragment_to_dashboardFragment)
    }
}