package com.example.moneykeeper.presentation;

import android.content.Intent;

import com.example.moneykeeper.presentation.about.AboutActivity;
import com.example.moneykeeper.presentation.chart.ChartActivity;
import com.example.moneykeeper.presentation.home.HomeActivity;
import com.example.moneykeeper.presentation.summary.SummaryActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.appcompat.app.AppCompatActivity;

import timber.log.Timber;

@Singleton
public class Navigator {
    @Inject
    public Navigator() {

    }

    public void openChartActivity(HomeActivity homeActivity) {
        Timber.d("openChartActivity");
        Intent intent = new Intent(homeActivity, ChartActivity.class);
        homeActivity.startActivity(intent);
    }

    public void openSummaryActivity(HomeActivity homeActivity) {
        Timber.d("openSummaryActivity");
        Intent intent = new Intent(homeActivity, SummaryActivity.class);
        homeActivity.startActivity(intent);
    }

    public void openAboutActivity(HomeActivity activity) {
        Timber.d("openAboutActivity");
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }
}
