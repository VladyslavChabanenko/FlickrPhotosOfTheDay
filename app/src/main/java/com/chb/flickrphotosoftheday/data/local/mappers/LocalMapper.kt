package com.chb.flickrphotosoftheday.data.local.mappers

interface LocalMapper<T, V> {

    fun mapFromCached(dbEntity: T): V

    fun mapToCached(entity: V): T
}
