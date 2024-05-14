package course.demoappfirebase.feature.auth.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import course.demoappfirebase.R
import course.demoappfirebase.ui.theme.DemoAppFirebaseTheme


@Composable
fun AuthHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.width(100.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )

        Text(

            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Column(
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "+")
            }
            Text(text = "1")
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "-")
            }
        }
        Text(text = "xoa")

    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppHeaderLight() {
    DemoAppFirebaseTheme {
        AuthHeader()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHeaderDark() {
    DemoAppFirebaseTheme {
        AuthHeader()
    }
}