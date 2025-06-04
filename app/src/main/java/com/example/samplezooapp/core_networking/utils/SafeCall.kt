package com.example.samplezooapp.core_networking.utils


import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import kotlinx.coroutines.ensureActive
import retrofit2.Response
import java.io.IOException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: ()-> Response<T>
): Result<T, ZooServiceError>{
    val response = try{
        execute()
    }catch (e: UnresolvedAddressException){
        return Result.Error(ZooServiceError.NO_INTERNET)
    }catch (e: JsonDataException){
        return Result.Error(ZooServiceError.MALFORMED_JSON_ERROR)
    }catch (e: JsonEncodingException){
        return Result.Error(ZooServiceError.MALFORMED_JSON_ERROR)
    }catch (e: IOException){
        return Result.Error(ZooServiceError.MALFORMED_JSON_ERROR)
    }catch (e: Exception){
        coroutineContext.ensureActive()
        return Result.Error(ZooServiceError.UNKNOWN_ERROR)
    }

    return responseToResult(response)
}


suspend inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T,ZooServiceError>{
    return when(response.code()){
        in 200 .. 299-> {
            try {
                Result.Success(response.body()?: throw JsonDataException())
            }catch (e: JsonDataException){
                Result.Error(ZooServiceError.MALFORMED_JSON_ERROR)
            }
        }
        408 -> Result.Error(ZooServiceError.REQUEST_TIMEOUT)
        429 -> Result.Error(ZooServiceError.TOO_MANY_REQUEST)
        in 500 .. 599 -> Result.Error(ZooServiceError.SERVER_ERROR)
        else -> Result.Error(ZooServiceError.UNKNOWN_ERROR)
    }
}