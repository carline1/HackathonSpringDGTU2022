package com.example.hackathonspring2022.di

import com.example.hackathonspring2022.di.modules.NetworkModule
import com.example.hackathonspring2022.ui.features.statistic_fragment.StatisticFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: StatisticFragment)
}