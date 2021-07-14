package com.dc.mvvmskeleton.data.model.hb

import com.google.gson.annotations.SerializedName

class WSGenericResponse<T> : HBBaseResponse() {
    @SerializedName("data")
    var data: T? = null
}
