package com.naphop.nestory.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naphop.nestory.R
import com.naphop.nestory.ui.theme.NestoryTheme
import com.naphop.nestory.ui.theme.NestoryTypography

@Composable
fun ScreenHeader(
    icon: Painter,
    title: String,
    description: String? = null
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            modifier = Modifier.size(88.dp),
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(16.dp))

        Text(
            text = title,
            style = NestoryTypography.HeaderLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        if(description != null){
            Text(
                text = description,
                style = NestoryTypography.BodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenHeaderPreview() {
    NestoryTheme {
        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            ScreenHeader(
                icon = painterResource(R.drawable.ic_nav_home_active),
                title = "Good Morning !"
            )
        }

    }
}