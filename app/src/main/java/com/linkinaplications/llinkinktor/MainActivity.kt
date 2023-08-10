package com.linkinaplications.llinkinktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkinaplications.llinkinktor.data.remote.PostsService
import com.linkinaplications.llinkinktor.data.remote.dto.PostResponse
import com.linkinaplications.llinkinktor.ui.theme.LlinkinKtorTheme

class MainActivity : ComponentActivity() {

    private val service = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val posts = produceState<List<PostResponse>>(initialValue = emptyList(), producer = {
                value = service.getPosts()
            })

            LlinkinKtorTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LazyColumn{
                        items(posts.value){
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)) {
                                Text(text = it.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.body, fontSize = 14.sp)
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