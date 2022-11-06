package com.elfirman.githubusersubmission.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.elfirman.githubusersubmission.R
import com.elfirman.githubusersubmission.data.Resource
import com.elfirman.githubusersubmission.databinding.ActivityDetailBinding
import com.elfirman.githubusersubmission.model.User
import com.elfirman.githubusersubmission.ui.adapter.FollowPagerAdapter
import com.elfirman.githubusersubmission.util.Constanta.EXTRA_USER
import com.elfirman.githubusersubmission.util.Constanta.TAB_TITLES
import com.elfirman.githubusersubmission.util.ViewStateCallback
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity(), ViewStateCallback<User?> {

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
        }

        val username = intent.getStringExtra(EXTRA_USER)

        viewModel.getDetailUser(username).observe(this, {
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> onSuccess(it.data)
            }
        })

        val pageAdapter = FollowPagerAdapter(this, username.toString())

        detailBinding.apply {
            viewPager.adapter = pageAdapter
            TabLayoutMediator(tabs, viewPager) { tabs, position ->
                tabs.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onSuccess(data: User?) {
        detailBinding.apply {
            tvDetailNumberOfRepos.text = data?.repository.toString()
            tvDetailNumberOfFollowers.text = data?.follower.toString()
            tvDetailNumberOfFollowing.text = data?.following.toString()
            tvDetailName.text = data?.name
            tvDetailCompany.text = data?.company
            tvDetailLocation.text = data?.location

            Glide.with(this@DetailActivity)
                .load(data?.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(ivDetailAvatar)

            supportActionBar?.title = data?.username
            detailProgressBar.visibility = invisible
        }
    }

    override fun onLoading() {
        detailBinding.apply {
            detailProgressBar.visibility = visible
            ivDetailAvatar.visibility = invisible
            tvDetailNumberOfRepos.visibility = invisible
            tvDetailNumberOfFollowers.visibility = invisible
            tvDetailNumberOfFollowing.visibility = invisible
            tvDetailName.visibility = invisible
            tvDetailCompany.visibility = invisible
            tvDetailLocation.visibility = invisible
            tvDetailFollowers.visibility = invisible
        }
    }

    override fun onFailed(message: String?) {
    }
}