package com.sevenpeakssoftware.krishna.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sevenpeakssoftware.krishna.adapter.CarsAdapter
import com.sevenpeakssoftware.krishna.databinding.FragmentCarsBinding
import com.sevenpeakssoftware.krishna.response.CarsModel
import com.sevenpeakssoftware.krishna.utils.NetworkResponse
import com.sevenpeakssoftware.krishna.utils.hide
import com.sevenpeakssoftware.krishna.utils.isNetworkAvailable
import com.sevenpeakssoftware.krishna.utils.show
import com.sevenpeakssoftware.krishna.utils.showSnackBar
import com.sevenpeakssoftware.krishna.viewmodel.CarsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CarsFragment : Fragment() {

    private lateinit var binding: FragmentCarsBinding
    private val viewModel: CarsViewModel by viewModels()

    @Inject
    lateinit var carsAdapter: CarsAdapter
    private val carsInfoList: MutableList<CarsModel?> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isConnected = isNetworkAvailable(requireContext())

        if (isConnected)
            viewModel.fetchCarDetailsData()
        else {
            viewModel.getAllCarsListFromLocalDB()
        }
        observeData()
        getAllCarsListFromLocalDB()
    }

    private fun getAllCarsListFromLocalDB() {
        viewModel.carsList.observe(viewLifecycleOwner) { response ->
            response?.let {
                setCarsInfoData(it)
            }
        }

    }

    private fun observeData() {
        viewModel.returnUserOutletTypesInfoData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Loading -> {
                    binding.progressBar.show()
                }

                is NetworkResponse.Success -> {
                    binding.progressBar.hide()
                    carsInfoList.clear()
                    binding.recyclerViewCars.adapter = null
                    response.data?.let {
                        if (it.status != null && it.status == "success") {
                            it.content?.let { carsData ->
                                carsInfoList.addAll(carsData)
                                Log.e("TAG", "observeData: ${carsInfoList.size}")
                            }
                            if (carsInfoList.size > 0) {
                                setCarsInfoDataToLocalDB(carsInfoList)
                            } else
                                binding.recyclerViewCars.adapter = null

                        } else {
                            binding.constraintCars.showSnackBar("No Data Available")
                        }
                    }
                }

                is NetworkResponse.Error -> {
                    binding.progressBar.hide()
                    binding.constraintCars.showSnackBar("No Data Available")
                }
            }
        }
    }

    private fun setCarsInfoDataToLocalDB(carsInfoList: MutableList<CarsModel?>) {
        lifecycleScope.launch {
            viewModel.deleteCarsListFromLocalDB()
            viewModel.saveAllCarsDetailsInLocalDB(carsInfoList)
        }
        setCarsInfoData(carsInfoList)
    }

    private fun setCarsInfoData(carsInfoList: MutableList<CarsModel?>) {
        carsAdapter = CarsAdapter()
        carsAdapter.differ.submitList(carsInfoList)
        binding.recyclerViewCars.adapter = carsAdapter
    }

}
