package com.chb.flickrphotosoftheday.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chb.flickrphotosoftheday.data.local.models.PhotoDBEntity
import com.chb.flickrphotosoftheday.data.local.models.PhotoInfoDBEntity

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos")
    fun getPhotos(): List<PhotoDBEntity>

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhoto(id: Long): PhotoDBEntity

    @Query("SELECT * FROM photoInfo")
    fun getPhotoInfoList(): List<PhotoInfoDBEntity>

    @Query("SELECT * FROM photoInfo WHERE id = :id")
    fun getPhotoInfo(id: Long): PhotoInfoDBEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePhotos(vararg photo: PhotoDBEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePhotoInfo(info: PhotoInfoDBEntity)

    @Transaction
    fun updateAllPhotos(photosToInsert: List<PhotoDBEntity>, photosToUpdate: List<PhotoDBEntity>, photosToDelete: List<PhotoDBEntity>) {
        photosToInsert.forEach {
            insert(it)
            insert(getPhotoInfo(it.id))
        }
        photosToUpdate.forEach {
            update(it)
            update(getPhotoInfo(it.id))
        }
        photosToDelete.forEach {
            delete(it)
            delete(getPhotoInfo(it.id))
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photos: PhotoDBEntity)

    @Update
    fun update(vararg photos: PhotoDBEntity)

    @Delete
    fun delete(photo: PhotoDBEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photos: PhotoInfoDBEntity)

    @Update
    fun update(vararg photos: PhotoInfoDBEntity)

    @Delete
    fun delete(photo: PhotoInfoDBEntity)
}
