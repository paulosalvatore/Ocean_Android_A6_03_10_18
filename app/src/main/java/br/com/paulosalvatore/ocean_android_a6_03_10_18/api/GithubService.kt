package br.com.paulosalvatore.ocean_android_a6_03_10_18.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
	@GET("/search/repositories")
	fun searchRepositories(
			@Query("q") query: String,
			@Query("sort") sort: String = "stars",
			@Query("order") order: String = "desc"
	): Call<GithubRepositoriesResult>
}
