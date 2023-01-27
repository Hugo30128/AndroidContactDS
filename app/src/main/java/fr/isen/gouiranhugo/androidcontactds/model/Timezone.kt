package fr.isen.gouiranhugo.androidcontactds.model

import com.google.gson.annotations.SerializedName


data class Timezone (

  @SerializedName("offset"      ) var offset      : String? = null,
  @SerializedName("description" ) var description : String? = null

):java.io.Serializable