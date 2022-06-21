package com.example.countries.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.countries.R
import com.example.countries.databinding.FragmentGoogleMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentGoogleMapBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var _mMap: GoogleMap? = null
    private val mMap get() = requireNotNull(_mMap)
    private val args: GoogleMapFragmentArgs by navArgs()
    private val mapFragment by lazy {
        childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentGoogleMapBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapFragment.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _mMap = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        _mMap = googleMap
        val country = args.country

        val countryСordinates = LatLng(
            country.capitalInfo.latlng.first(),
            country.capitalInfo.latlng.last()
        )
        val marker = MarkerOptions()
            .position(countryСordinates)
            .title(country.name.common)

        mMap.addMarker(marker)
        mMap.setOnMarkerClickListener {
            val action = GoogleMapFragmentDirections.actionGoogleMapFragmentToBottomSheetFragment(
                country
            )
            findNavController().navigate(action)
            true
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(countryСordinates))
    }


}