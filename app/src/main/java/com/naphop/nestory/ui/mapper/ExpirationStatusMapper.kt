package com.naphop.nestory.ui.mapper

import com.naphop.nestory.domain.model.ExpirationStatus
import com.naphop.nestory.ui.theme.StatusBadge

fun ExpirationStatus.toBadgeType(): StatusBadge {
    return when (this) {
        ExpirationStatus.EXPIRED -> StatusBadge.EXPIRED
        ExpirationStatus.THIS_WEEK -> StatusBadge.SOON
        ExpirationStatus.THIS_MONTH -> StatusBadge.SOON
        ExpirationStatus.FRESH -> StatusBadge.OK
    }
}