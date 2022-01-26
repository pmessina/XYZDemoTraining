package com.cooperlighting.xyzdemotraining

import android.app.Application
import com.cooperlighting.xyzdemotraining.ProductsViewModel
import com.cooperlighting.xyzdemotraining.data.ProductsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class XYZDemoTrainingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@XYZDemoTrainingApplication)
            modules(appModule)
        }
    }

    private val appModule = module {
        single { ProductsRepository() }
        single { ProductsViewModel(get()) }
    }
}