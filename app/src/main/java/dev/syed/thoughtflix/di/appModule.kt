package dev.syed.thoughtflix.di

import dev.syed.thoughtflix.BuildConfig
import dev.syed.thoughtflix.data.remote.AuthInterceptor
import dev.syed.thoughtflix.feature.auth.signin.AuthenticationViewModel
import dev.syed.thoughtflix.feature.auth.signup.SignUpViewModel
import dev.syed.thoughtflix.feature.home.HomeViewmodel
import dev.syed.thoughtflix.feature.hotandnew.HotAndNewViewmodel
import dev.syed.thoughtflix.feature.moviedetail.MovieDetailViewModel
import dev.syed.thoughtflix.feature.search.SearchViewmodel
import dev.syed.thoughtflix.network.MovieApiService
import dev.syed.thoughtflix.repository.MovieRepository
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModelModule = module {
    viewModel { AuthenticationViewModel() }
    viewModel { SignUpViewModel() }
    viewModel { HomeViewmodel(get()) }
    viewModel { SearchViewmodel(get()) }
    viewModel { HotAndNewViewmodel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}

val repositoryModule = module {
    single { MovieRepository(get()) }
}


val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideMovieApiServices(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideMovieApiServices(retrofit: Retrofit): MovieApiService = retrofit.create(MovieApiService::class.java)

val appModule: List<Module> = listOf(repositoryModule, viewModelModule, networkModule)

