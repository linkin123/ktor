package com.linkinaplications.llinkinktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linkinaplications.llinkinktor.data.remote.HttpRoutes.API_KEY
import com.linkinaplications.llinkinktor.data.remote.PostsService
import com.linkinaplications.llinkinktor.data.remote.dto.MovieList
import com.linkinaplications.llinkinktor.ui.theme.LlinkinKtorTheme

class MainActivity : ComponentActivity() {

    private val service = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val posts = produceState(initialValue = MovieList(), producer = {
                value = service.getPosts(API_KEY)
            })

            LlinkinKtorTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LazyColumn {
                        items(posts.value.results) {
                            Column {
                                Card(
                                    border = BorderStroke(2.dp, Color.Black),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 8.dp,
                                            bottom = 8.dp,
                                            end = 8.dp,
                                            start = 8.dp
                                        )
                                ) {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500/${it.backdrop_path}",
                                        contentDescription = "poster",
                                        modifier = Modifier.fillMaxWidth(),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = it.original_title,
                                        modifier = Modifier.align(Alignment.CenterHorizontally),
                                        style = MaterialTheme.typography.titleMedium,
                                        fontSize = 25.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LlinkinKtorTheme {
        Greeting("Android")
    }
}