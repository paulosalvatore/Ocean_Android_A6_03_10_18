package br.com.paulosalvatore.ocean_android_a6_03_10_18

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.paulosalvatore.ocean_android_a6_03_10_18.api.GithubRepositoriesResult
import br.com.paulosalvatore.ocean_android_a6_03_10_18.api.RepositoryRetriever
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

	private val repositoryRetriever = RepositoryRetriever()

	private val callback = object : Callback<GithubRepositoriesResult> {
		override fun onFailure(call: Call<GithubRepositoriesResult>?, t: Throwable?) {
			longToast("Fail loading repositories.")

			Log.e("MainActivity", "Problem calling GitHub API", t)
			Log.d("MainActivity", "Fail on URL: ${call?.request()?.url()}")
		}

		override fun onResponse(call: Call<GithubRepositoriesResult>?, response: Response<GithubRepositoriesResult>?) {
			longToast("Load finished.")

			response?.isSuccessful.let {
				response?.body()?.repositories?.let {
//					val resultList = response.body()?.repositories ?: emptyList()
					textView.text = ""
					it.forEach {
						textView.append("${it.full_name}\n")
					}
				}
			}
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		repositoryRetriever.getLanguageRepositories(callback, "kotlin")
	}
}
