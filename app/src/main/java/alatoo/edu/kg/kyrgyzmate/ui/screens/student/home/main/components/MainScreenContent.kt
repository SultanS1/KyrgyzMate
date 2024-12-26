package alatoo.edu.kg.kyrgyzmate.ui.screens.student.home.main.components

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.ui.theme.White
import alatoo.edu.kg.kyrgyzmate.ui.theme.coolBlue
import alatoo.edu.kg.kyrgyzmate.ui.theme.headlineSmall
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {

        Row (
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "Courses",
                style = headlineSmall,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier.size(31.dp),
                painter = painterResource(id = R.drawable.ic_kg_flag),
                contentDescription = "Main page icon",
                colorFilter = null
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 16.dp
            )
        ) {
            items(5) { item ->
                CourseBlock(
                    title = "Beginner",
                    description = "sit amet, consectetur adipiscing elit, sed do eiusmod tempor sit amet, consectetur a dipiscing elit, sed do eiusmod tempor dipiscing elit, sed do eiusmod tempor sed do eiusmod tempor",
                    percentagePassed = 90,
                    amountOfLessons = "$item lessons",
                    rootBackgroundColor = coolBlue
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    MainScreenContent()
}