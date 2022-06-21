package com.example.countries.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.countries.R
import com.example.countries.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val args: BottomSheetFragmentArgs by navArgs()

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return BottomSheetLayoutBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onStart() {
        super.onStart()

        val density = requireContext().resources.displayMetrics.density

        dialog?.let {
            val bottomSheet =
                it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.peekHeight = (COLLAPSED_HEIGHT * density).toInt()
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {

                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    with(binding) {
                        if (slideOffset > 0) {
                            layoutCollapsed.alpha = 1 - 2 * slideOffset
                            layoutExpanded.alpha = slideOffset * slideOffset

                            if (slideOffset > 0.5) {
                                layoutCollapsed.visibility = View.GONE
                                layoutExpanded.visibility = View.GONE
                            }

                            if (slideOffset < 0.5 && binding.layoutCollapsed.visibility == View.VISIBLE) {
                                layoutCollapsed.visibility = View.VISIBLE
                                layoutExpanded.visibility = View.INVISIBLE
                            }
                        }
                    }
                }

            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val country = args.countryName

        with(binding) {
            imageFlag.load(country.flags.png)
            tvCountryName.text = country.name.common
            tvPopulation.text = "Population: ${country.population}"
            tvCapital.text = "Capital: ${country.capital.first()}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding
    }

    companion object {
        private const val COLLAPSED_HEIGHT = 228
    }
}