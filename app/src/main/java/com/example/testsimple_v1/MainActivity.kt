package com.example.testsimple_v1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testsimple_v1.ui.theme.TestSimple_V1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        val people  = listOf(
//            Person("Garen","Arabkirlian",26),
//            Person("Cynthia","Malatian",23),
//            Person("Garen","Arabkirlian",43),
//            Person("Garen","Arabkirlian",11),
//            Person("Cynthia","Malatian",76),
//            Person("Garen","Arabkirlian",15),
//            Person("Garen","Arabkirlian",16),
//            Person("Cynthia","Malatian",19),
//            Person("Garen","Arabkirlian",21),
//            Person("Garen","Arabkirlian",10),
//            Person("Garen","Arabkirlian",20)
//        )

//        val peopleFiler = people.filter{it.age >= 21 && it.lastName == "Malatian"}

        val rssItems = listOf(
            RSSItem("Welcome to Austin Texas, we have music!", "There is a lot of music her in Austin Texas.", RSSType.TEXT),
            RSSItem("Welcome to my photo gallery!", "Click her for gallery.", RSSType.IMAGE, R.drawable.patek_moonphase),
            RSSItem("Welcome to my press conference!", "Press conference happening right now!", RSSType.VIDEO,R.drawable.rolex_daytona),
            RSSItem("Welcome to my photo gallery!", "Click her for gallery.", RSSType.IMAGE, R.drawable.patek_moonphase),
            RSSItem("Welcome to my press conference!", "Press conference happening right now!", RSSType.VIDEO,R.drawable.rolex_daytona),
            RSSItem("Welcome to my photo gallery!", "Click her for gallery.", RSSType.IMAGE, R.drawable.patek_moonphase),
        )
        setContent {
            TestSimple_V1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        LazyColumn {
                            items(rssItems) {
                                when (it.type) {
                                    RSSType.TEXT -> {
                                        RSSItemText(it)
                                    }

                                    RSSType.VIDEO -> {
                                        RSSItemVideo(it)
                                    }

                                    RSSType.IMAGE -> {
                                        RSSItemImage(it)
                                    }
                                }
                            }
                        }
                        SearchBox()
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
        TestSimple_V1Theme {
            Greeting("Android")
        }
    }

    @Composable
    fun CardView(person: Person) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(R.drawable.baseline_person_24),
                    "Picture of person",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
                Column {
                    Text(
                        text = person.firstName,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = person.lastName,
                        modifier = Modifier.padding(0.dp)
                    )
                    Text(
                        text = "Age: " + person.age,
                        modifier = Modifier.padding(0.dp)
                    )
                }

            }
        }
    }

    @Composable
    fun RSSItemText(rssItem: RSSItem){
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = rssItem.title,
                fontSize = 32.sp,
                lineHeight =  32.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
            Text(
                text = rssItem.text,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
        }
    }

    @Composable
    fun RSSItemVideo(rssItem: RSSItem){
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = rssItem.title,
                fontSize = 32.sp,
                lineHeight =  32.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
            Text(
                text = rssItem.text,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
            rssItem.media?.let{photo ->
                Image(
                    painter = painterResource(photo),

                    "Picture of person",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }

    @Composable
    fun RSSItemImage(rssItem: RSSItem) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = rssItem.title,
                fontSize = 32.sp,
                lineHeight =  32.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clickable {
                        Log.d("dimalone","Photo tapped!")
                    }
            )
            Text(
                text = rssItem.text,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
            rssItem.media?.let{photo ->
                Image(
                    painter = painterResource(photo),
                    "Picture of person",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }

    @Composable
    fun SearchBox(){
        var searchQuery by remember { mutableStateOf("Search") }

        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            singleLine = true,
            modifier = Modifier
                .padding(top  = 26.dp)
                .fillMaxWidth()
        )
    }

    @Composable
    fun SearchBar() {
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Search") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        )
    }
}


