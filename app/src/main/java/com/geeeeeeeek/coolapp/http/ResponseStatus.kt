package com.fsck.k9.http

object ResponseStatus {
    /**
     * 响应成功
     */
    const val SUCCESS = "S_OK"

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR = "1002"

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = "1003"

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = "1004"


}