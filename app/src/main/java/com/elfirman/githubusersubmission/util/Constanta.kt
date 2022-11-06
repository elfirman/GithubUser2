package com.elfirman.githubusersubmission.util

import androidx.annotation.StringRes
import com.elfirman.githubusersubmission.R

object Constanta {
    const val TIME_SPLASH = 1500L

    const val EXTRA_USER = "EXTRA_USER"

    @StringRes
    val TAB_TITLES = intArrayOf(
        R.string.followers,
        R.string.following
    )

    const val GITHUB_TOKEN = "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"

    const val BASE_URL = "https://api.github.com"
}