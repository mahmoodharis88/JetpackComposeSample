package com.sample.jetpackcomposesample.domain.usecases.app_entry

import com.sample.jetpackcomposesample.domain.manger.LocalUserManger
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }

}