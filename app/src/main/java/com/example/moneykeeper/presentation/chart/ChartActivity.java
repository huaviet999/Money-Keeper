package com.example.moneykeeper.presentation.chart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by Viet Hua on 3/11/2020
 */
public class ChartActivity extends BaseActivity implements ChartContract.View {
    @Inject
    ChartContract.Presenter presenter;

    public static void startChartActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, ChartActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        AnimatedPieView mAnimatedPieView = findViewById(R.id.chart_pie);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.strokeWidth(150);
        config.textSize(60);
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(30, Color.parseColor("#ff0000"), "Income"))//Data (bean that implements the IPieInfo interface)
                .addData(new SimplePieInfo(18.0f, Color.parseColor("#00ff00"), "Expense")).drawText(true)
      .duration(1000);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}
