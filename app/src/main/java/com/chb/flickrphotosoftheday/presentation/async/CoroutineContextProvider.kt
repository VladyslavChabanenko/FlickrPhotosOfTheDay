package com.chb.flickrphotosoftheday.presentation.async

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface ICoroutineContextProvider {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
}

class CoroutineContextProvider @Inject constructor() : ICoroutineContextProvider {
    override val io = Dispatchers.IO
    override val default = Dispatchers.Default
    override val main: CoroutineDispatcher = Dispatchers.Main
}
