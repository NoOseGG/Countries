package com.example.countries.screen

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.countries.databinding.FragmentListCountryBinding
import com.example.countries.viewmodel.ListCountryViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCountryFragment : Fragment() {

    private var _binding: FragmentListCountryBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: ListCountryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentListCountryBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onStart() {
        super.onStart()
        binding.edCountryName.setText("")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.flow.onEach {

            val action = ListCountryFragmentDirections.actionListCountryFragmentToGoogleMapFragment(
                it.first()
            )
            findNavController().navigate(action)

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        with(binding) {
            btnSearch.setOnClickListener {
                viewModel.search(binding.edCountryName.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}