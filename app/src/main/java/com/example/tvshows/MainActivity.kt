package com.example.tvshows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tvshows.tvshows_detail.presentation.TvShowDetailScreen
import com.example.tvshows.tvshows_list.presentation.TvShowsListScreen
import com.example.tvshows.ui.navigation.TvShowDetail
import com.example.tvshows.ui.navigation.TvShowList
import com.example.tvshows.ui.theme.TvShowsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvShowsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = TvShowList) {
                    composable<TvShowList> {
                        TvShowsListScreen(
                            modifier = Modifier.fillMaxSize(),
                            onShowSelected = { navController.navigate(TvShowDetail(it.id, it.name)) }
                        )
                    }
                    composable<TvShowDetail> {
                        val args = it.toRoute<TvShowDetail>()
                        TvShowDetailScreen(
                            tvShowId = args.tvShowId,
                            tvShowName = args.tvShowName,
                            onBack = { navController.popBackStack() })
                    }
                }

            }
        }
    }
}