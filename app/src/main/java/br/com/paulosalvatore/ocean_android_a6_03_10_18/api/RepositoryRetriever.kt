package br.com.paulosalvatore.ocean_android_a6_03_10_18.api

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryRetriever {
	private val service: GithubService

	companion object {
		const val BASE_URL = "https://api.github.com/";
	}

	init {
		val retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(
						GsonConverterFactory.create()
				).build()

		service = retrofit.create(GithubService::class.java)
	}

	fun getLanguageRepositories(callback: Callback<GithubRepositoriesResult>,
	                            query: String) {
		val call = service.searchRepositories("language:$query")
		call.enqueue(callback)
	}
}
