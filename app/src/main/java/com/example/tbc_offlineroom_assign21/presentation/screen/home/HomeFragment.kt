package com.example.tbc_offlineroom_assign21.presentation.screen.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_offlineroom_assign21.databinding.FragmentHomeBinding
import com.example.tbc_offlineroom_assign21.presentation.event.home.HomeEvents
import com.example.tbc_offlineroom_assign21.presentation.screen.base.BaseFragment
import com.example.tbc_offlineroom_assign21.presentation.screen.home.recyclerAdapters.SectionsRvAdapter
import com.example.tbc_offlineroom_assign21.presentation.screen.home.recyclerAdapters.ShopItemsRvAdapter
import com.example.tbc_offlineroom_assign21.presentation.state.home.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var sectionsRvAdapter: SectionsRvAdapter
    private lateinit var shopItemsRvAdapter: ShopItemsRvAdapter

    override fun setUp() {
        setUpSectionsRv()
        setUpShopItemsRv()
        listeners()
        bindObservers()
    }

    private fun setUpSectionsRv() {
        sectionsRvAdapter = SectionsRvAdapter()
        binding.rvSections.apply {
            adapter = sectionsRvAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpShopItemsRv() {
        shopItemsRvAdapter = ShopItemsRvAdapter()
        binding.rvShopItems.apply {
            adapter = shopItemsRvAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun listeners() {
        sectionsRvAdapter.itemOnClick = {viewModel.onEvent(HomeEvents.SectionItemPressed(it))}
        shopItemsRvAdapter.itemOnClick = {viewModel.onEvent(HomeEvents.HeartPressed(it))}
    }

    private fun handleState(state: HomeState) {
        state.sections?.let {
            sectionsRvAdapter.setData(state.sections)
            sectionsRvAdapter.notifyItemRangeChanged(0, state.sections.size)
        }

        state.data?.let {
            shopItemsRvAdapter.setData(state.data)
            shopItemsRvAdapter.notifyItemRangeChanged(0, state.data.size)
        }
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleState(it)
                }
            }
        }
    }
}