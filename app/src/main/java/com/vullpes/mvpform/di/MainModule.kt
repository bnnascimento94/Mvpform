package com.vullpes.mvpform.di

import android.app.Activity
import com.vullpes.mvpform.contract.MainViewContract
import com.vullpes.mvpform.model.repository.data.AddressApi
import com.vullpes.mvpform.model.repository.data.GetCep
import com.vullpes.mvpform.model.repository.remote.CepApi
import com.vullpes.mvpform.presenter.Presenter
import com.vullpes.mvpform.view.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindActivity(activity: MainActivity): MainViewContract.IFormView

}

@InstallIn(ActivityComponent::class)
@Module
abstract class AddressApiDataSourceModule {

    @Binds
    abstract fun bindDataSource(
        impl: GetCep
    ): AddressApi

}


@InstallIn(ActivityComponent::class)
@Module
object MainActivityModule {

    @Provides
    fun bindActivity(activity: Activity): MainActivity {
        return activity as MainActivity
    }

    @Provides
    fun bindPresenter(view: MainViewContract.IFormView, addressApi: AddressApi ):  MainViewContract.IPresenter {
        return Presenter(
            view, addressApi, Dispatchers.IO, Dispatchers.Main
        )
    }
}


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun providesNewsApi(): CepApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://viacep.com.br")
            .build()
            .create(CepApi::class.java)
    }



}