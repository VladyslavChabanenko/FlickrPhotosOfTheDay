package com.chb.flickrphotosoftheday.data.remote.mappers

interface RemoteMapper<M, E> {

    fun mapFromModel(model: M): E
}
