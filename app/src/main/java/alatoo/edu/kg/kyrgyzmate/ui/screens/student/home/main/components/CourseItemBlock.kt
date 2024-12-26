package alatoo.edu.kg.kyrgyzmate.ui.screens.student.home.main.components

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.extensions.toPercentage
import alatoo.edu.kg.kyrgyzmate.extensions.toProgressType
import alatoo.edu.kg.kyrgyzmate.ui.theme.White
import alatoo.edu.kg.kyrgyzmate.ui.theme.bodyMedium
import alatoo.edu.kg.kyrgyzmate.ui.theme.coolBlue
import alatoo.edu.kg.kyrgyzmate.ui.theme.extraSmall
import alatoo.edu.kg.kyrgyzmate.ui.theme.labelLarge
import alatoo.edu.kg.kyrgyzmate.ui.theme.skyBlue
import alatoo.edu.kg.kyrgyzmate.ui.theme.titleMedium
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CourseBlock(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    percentagePassed: Int,
    amountOfLessons: String,
    rootBackgroundColor: Color
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(rootBackgroundColor, RoundedCornerShape(16.dp))
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 16.dp)
    ){
        Text(
            text = title,
            style = titleMedium,
            color = Color.White,
            maxLines = 1
        )
        Text(
            modifier = modifier.padding(top = 8.dp, bottom = 16.dp),
            text = description,
            style = bodyMedium,
            color = White,
            maxLines = 5,
            textAlign = TextAlign.Start
        )

        Row (
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box (
                modifier = modifier
                    .background(skyBlue, RoundedCornerShape(39.dp))
                    .wrapContentSize()
            ){
                Text(
                    modifier = modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                    text = amountOfLessons,
                    style = extraSmall,
                    color = White
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = modifier.wrapContentSize(),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "Progress Rate Icon",
                colorFilter = null
            )

            Text(
                modifier = modifier.padding(horizontal = 4.dp),
                text = percentagePassed.toPercentage(),
                style = labelLarge,
                color = White
            )

            ProgressLine(
                modifier = modifier,
                progressPercentage = percentagePassed.toProgressType()
            )
        }
    }
}

@Composable
fun ProgressLine(
    modifier: Modifier = Modifier,
    progressPercentage: Float
){
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(6.dp))
            .background(Color.Black.copy(alpha = 0.24f))
    ) {
        LinearProgressIndicator(
            progress = progressPercentage,
            modifier = modifier
                .width(33.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(6.dp)),
            color = Color.White,
            trackColor = Color.Transparent
        )
    }
}

@Preview
@Composable
fun PreviewGreeting() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(30.dp)
    ) {
        CourseBlock(
            title = "Beginner",
            description = "Description of course to be here",
            percentagePassed = 90,
            amountOfLessons = "6 lessons",
            rootBackgroundColor = coolBlue
        )
    }
}