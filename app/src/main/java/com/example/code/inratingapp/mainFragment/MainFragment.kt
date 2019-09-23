package com.example.code.inratingapp.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.inratingapp.R
import com.example.code.inratingapp.adapterStatistic.AdapterStatistic
import com.example.code.inratingapp.adapterStatistic.ItemStatistic
import com.example.code.inratingapp.adapterUsers.AdapterUsers
import com.example.code.inratingapp.api.apiData.Data
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        const val FRAGMENT_MAIN = "mainFragment"
    }


    private lateinit var viewModel: ViewModelMainFragment
    private val adapters = ArrayList<AdapterUsers>()
    private val data = ArrayList<ItemStatistic>()
    private lateinit var adapter: AdapterStatistic


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View model
        viewModel = ViewModelProviders.of(this).get(ViewModelMainFragment::class.java)

        // Adapter likes
        val liveDataLikes: LiveData<PagedList<Data>> = viewModel.getDataLikes()
        val adapterUsersLikes = AdapterUsers(null)
        liveDataLikes.observe(this, Observer<PagedList<Data>> {
            adapterUsersLikes.submitList(it)
        })
        adapters.add(adapterUsersLikes)

        // Adapter likes
        val liveDataReposts: LiveData<PagedList<Data>> = viewModel.getDataReposts()
        val adapterUsersReposts = AdapterUsers(null)
        liveDataReposts.observe(this, Observer<PagedList<Data>> {
            adapterUsersReposts.submitList(it)
        })
        adapters.add(adapterUsersReposts)

        // Adapter likes
        val liveDataCommentators: LiveData<PagedList<Data>> = viewModel.getDataCommentators()
        val adapterUsersCommentators = AdapterUsers(null)
        liveDataCommentators.observe(this, Observer<PagedList<Data>> {
            adapterUsersCommentators.submitList(it)
        })
        adapters.add(adapterUsersCommentators)

        // Adapter likes
        val liveDataMentions: LiveData<PagedList<Data>> = viewModel.getDataMentions()
        val adapterUsersMentions = AdapterUsers(null)
        liveDataMentions.observe(this, Observer<PagedList<Data>> {
            adapterUsersMentions.submitList(it)
        })
        adapters.add(adapterUsersMentions)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Main data
        titles.forEachIndexed { index, resource ->
            data.add(ItemStatistic(getString(resource), adapters[index]))
        }
        // Main adapter
        adapter = AdapterStatistic(data)
        rvListStatistic.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvListStatistic.adapter = adapter

    }


    fun invalidateData() {
        viewModel.invalidateData()
    }

}