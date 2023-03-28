package com.example.mymovies.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mymovies.R
import com.example.mymovies.adapter.ViewAdapter
import com.example.mymovies.databinding.FragmentViewBinding

class ViewFragment<T>(private val list: List<T>) : Fragment() {
    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcView.adapter = ViewAdapter(list,requireContext())

    }


}