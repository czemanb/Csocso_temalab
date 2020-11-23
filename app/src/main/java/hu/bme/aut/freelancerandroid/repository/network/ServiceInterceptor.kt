package hu.bme.aut.freelancerandroid.repository.network

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor() : Interceptor {

    lateinit var token : String

    fun Token(token: String ) {
        this.token = token;
    }

    override fun intercept(chain: Interceptor.Chain): Response

    {
        var request = chain.request()

        if(request.header("No-Authentication")==null){

            if(!token.isNullOrEmpty())
            {
                val finalToken =  "Bearer " + token
                request = request.newBuilder()
                    .addHeader("Authorization",finalToken)
                    .build()
            }

        }

        return chain.proceed(request)
    }

}