package com.naphop.nestory.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naphop.nestory.ui.theme.NestoryIcons
import com.naphop.nestory.ui.theme.NestoryTheme
import com.naphop.nestory.ui.theme.NestoryTypography

@Composable
fun NestoryTopBar(
    title: String,
    onBack: () -> Unit = {}
) {
    // ใช้ Column หรือ Box เพื่อแยกส่วนเนื้อหากับเส้นขอบล่าง
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // ความสูงมาตรฐาน
            contentAlignment = Alignment.Center
        ) {
            // ปุ่ม Back (ซ้ายสุด)
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
            ) {
                Icon(
                    painter = NestoryIcons.Back,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Title (กึ่งกลาง)
            Text(
                text = title,
                style = NestoryTypography.TitleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // เส้นขอบล่าง (Divider) ตามดีไซน์
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun NestoryTopBarPreview() {
    NestoryTheme {
        NestoryTopBar(
            title = "Expiration List"
        )
    }
}
