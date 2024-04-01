package com.vullpes.mvpform.di

import com.vullpes.mvpform.FakeAddressApi
import com.vullpes.mvpform.FakePresenter
import com.vullpes.mvpform.contract.MainViewContract
import com.vullpes.mvpform.model.repository.data.AddressApi
import com.vullpes.mvpform.view.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ActivityComponent::class],
    replaces = [MainModule::class]
)
abstract class TestMainModule {

    @Binds
    abstract fun bindActivity(activity: MainActivity): MainViewContract.IFormView

    @Binds
    abstract fun bindPresenter(impl: FakePresenter): MainViewContract.IPresenter

}

@Module
@TestInstallIn(
    components = [ActivityComponent::class],
    replaces = [AddressApiDataSourceModule::class]
)
class TestAddressApiDataSourceModule {

    @Provides
    fun bindDataSource(
    ): AddressApi {
        return FakeAddressApi()
    }

}