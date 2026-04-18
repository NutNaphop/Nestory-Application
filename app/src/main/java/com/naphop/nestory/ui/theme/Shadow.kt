package com.naphop.nestory.ui.theme

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dropShadow(
    color: Color = Color.Black.copy(alpha = 0.1f),
    borderRadius: Dp = 0.dp,
    blur: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = drawBehind {
    drawIntoCanvas { canvas ->
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        if (blur.toPx() > 0) {
            frameworkPaint.maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
        frameworkPaint.color = color.toArgb()

        val spreadPx = spread.toPx()
        val left = offsetX.toPx() - spreadPx
        val top = offsetY.toPx() - spreadPx
        val right = size.width + offsetX.toPx() + spreadPx
        val bottom = size.height + offsetY.toPx() + spreadPx

        canvas.drawRoundRect(
            left = left,
            top = top,
            right = right,
            bottom = bottom,
            radiusX = borderRadius.toPx(),
            radiusY = borderRadius.toPx(),
            paint = paint
        )
    }
}

// 1. Drop-card: X=0, Y=5, Blur=10
fun Modifier.dropCard(borderRadius: Dp = NestoryDimens.RadiusMedium) = this.dropShadow(
    blur = 10.dp,
    offsetY = 5.dp,
    borderRadius = borderRadius
)

// 2. Drop-Thumb: X=0, Y=10, Blur=30
fun Modifier.dropThumb(borderRadius: Dp = NestoryDimens.RadiusMedium) = this.dropShadow(
    blur = 30.dp,
    offsetY = 10.dp,
    borderRadius = borderRadius
)

// 3. Drop-Popup: X=0, Y=10, Blur=30
fun Modifier.dropPopup(borderRadius: Dp = NestoryDimens.RadiusLarge) = this.dropShadow(
    blur = 30.dp,
    offsetY = 10.dp,
    borderRadius = borderRadius
)
