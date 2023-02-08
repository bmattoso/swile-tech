package com.br.swile.tech.core.util

sealed class OperationStatus {
    object Success : OperationStatus()
    class Failed(val throwable: Throwable) : OperationStatus()
}
