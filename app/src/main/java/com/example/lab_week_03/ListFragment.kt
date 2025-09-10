package com.example.lab_week_03

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView // Import TextView if not already
import androidx.navigation.fragment.findNavController // Import for navigation

interface CoffeeListener {
    fun onSelected(coffeeId: Int, coffeeName: String) // Modified to pass coffee name
}

class ListFragment : Fragment(), View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var coffeeListener: CoffeeListener;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CoffeeListener) {
            coffeeListener = context
        } else {
            throw RuntimeException("$context must implement CoffeeListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1) // Now ARG_PARAM1 is accessible
            param2 = it.getString(ARG_PARAM2) // Now ARG_PARAM2 is accessible
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coffeeList = listOf<View>(
            view.findViewById(R.id.affogato),
            view.findViewById(R.id.americano),
            view.findViewById(R.id.latte),
            view.findViewById(R.id.cappuccino), // New item
            view.findViewById(R.id.espresso)    // New item
        )

        coffeeList.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        v?.let { itemView ->
            val coffeeName = when (itemView.id) {
                R.id.affogato -> "Affogato"
                R.id.americano -> "Americano"
                R.id.latte -> "Latte"
                R.id.cappuccino -> "Cappuccino"
                R.id.espresso -> "Espresso"
                else -> "Unknown Coffee"
            }
            coffeeListener.onSelected(itemView.id, coffeeName)
        }
    }

    companion object {
        // Define the constants here
        private const val ARG_PARAM1 = "param1" // Make them const val
        private const val ARG_PARAM2 = "param2" // Make them const val

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
    