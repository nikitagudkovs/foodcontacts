package com.ng.test.foodcontacts.data.local.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Recipes")
data class RecipeEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("idMeal")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    @SerializedName("strMeal")
    var name: String = "",

    @ColumnInfo(name = "category")
    @SerializedName("strCategory")
    var category: String = "",

    @ColumnInfo(name = "area")
    @SerializedName("strArea")
    var area: String = "",

    @ColumnInfo(name = "instructions")
    @SerializedName("strInstructions")
    var instructions: String = "",

    @ColumnInfo(name = "img")
    @SerializedName("strMealThumb")
    var img: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeString(area)
        parcel.writeString(instructions)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeEntity> {
        override fun createFromParcel(parcel: Parcel): RecipeEntity {
            return RecipeEntity(parcel)
        }

        override fun newArray(size: Int): Array<RecipeEntity?> {
            return arrayOfNulls(size)
        }
    }
}