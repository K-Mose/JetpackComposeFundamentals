# Jetpack Compose - Recycler View
기존의 XML로 `RecyclerView`를 생성하기 위해서는 item의 xml과 recyclerView가 들어갈 activity의 xml이 필요했습니다. 그리고 `RecyclerViewAdpater`를 생성하고 `ViewHolder`를 추가하며 아주 복잡하게 생성되었습니다. 

Jetpack Compose를 이용하여 Recycler View의 생성을 간단하게 작성해보겠습니다. 

## Basic List
`Column`으로 100개의 `Text`를 생성해보겠습니다.
```kotlin 
class RecyclerViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableColumnDemo()
        }
    }
}

@Composable
fun ScrollableColumnDemo() {
    Column {
        for (i in 1 .. 100) {
            Text(text = "Card No.$i",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp))
            Divider(color = Color.Black, thickness = 5.dp )
        }
    }
}
```

이렇게 생성하면 `Text`는 100개가 생성이 되긴 하지만 스크롤이 되진 않습니다. 
Jetpack Compose의 Layout에서 스크롤이 되기 위해선 아래의 조건이 필요합니다. 
- ScrollState
- Vertical/Horizontal Scroll modifier
`ScrollState`는 변경되는 스크롤의 위치와 현재 스크롤의 상태를 저장하고, `[Vertical|Horizontal]Scroll` modifier는 Layout에 스크롤을 추가시킵니다. 

```kotlin
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
……
@Composable
fun ScrollableColumnDemo() {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ) {
      ……
    }
}
```
이제 생성된 `Column` Layout에서 스크롤리 가능해졌습니다. 

## LazyColumn
하지만 위에 처럼 리스트를 생성한다면 엄청난 수의 리스트가 복잡한 뷰를 가지고 동시에 화면에 생성된다면 메모리소모가 엄청날 것입니다. 
그렇다면 기존의 `RecyclerView`와 같은 기능을 하는 [`LazyColumn`](https://developer.android.com/jetpack/compose/lists#lazy)을 사용하면 됩니다. 


### Basic LazyColumn
위의 `Column`으로 생성한 리스트 아이템들을 `LazyColumn`으로 똑같이 생성해보겠습니다. 
```kotlin 
import androidx.compose.foundation.lazy.LazyColumn
……
@Composable
fun LazyColumnDemo() {
        LazyColumn {
        items(100) { 
            Text(text = "Card No.$it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp))
            Divider(color = Color.Black, thickness = 5.dp )
        }
    }
}
```
`LazyColumn`에는 `items`함수가 있어 사용할 뷰들의 갯수를 정하고 생성할 뷰들을 람다식으로 받습니다. 


### Item Click Listener
리스트에 Item Click Listener를 적용하기 위해서는 아래와 같이 `Composable` 함수에 람다식으로 파라메터를 건네주고, Modifier의 `clickable`에 파라메터를 넘겨주면 됩니다. 
```kotlin 
@Composable
fun LazyColumnDemo2(selectedItem: (String)->Unit) {
        LazyColumn {
        items(100) { // instead of using a for loop
            Text(text = "Card No.$it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp)
                    .clickable {
                        selectedItem("$it Selected")
                    }
            )
            Divider(color = Color.Black, thickness = 5.dp )
        }
    }
}
```
이제 클릭을 구현하기 위해서 `onCreate`함수에서 `LazyColumnDemo2`함수에 람다식의 인자값을 넘겨줍니다. 
```kotln
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnDemo2 {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
```

![image](https://user-images.githubusercontent.com/55622345/169683101-fb9cb50a-c35a-4303-944b-78302864758b.png)


### Item With Multiple Compose
이제 한 레이아웃 안에서 여러개의 Compose를 사용해서 좀 더 복잡한 구조로 만들어보겠습니다. 

[TMDBClient](https://github.com/K-Mose/TMDBClient---MVVM-with-Clean-Architecture/tree/master/app/src/main/java/com/kmose/tmdbclient/model/tvshow)에서 사용한 Data Model으로 `TvShow` 리스트를 작성해봅시다. 

<details>
<summary>Data Class</summary>

```kotlin
import java.io.Serializable

data class TvShow(
    val id: Int,
    val name: String,
    val year: Int,
    val rating : Double,
    val imageId : Int,
    val overview : String
    ) : Serializable
    
    
    
object TvShowList {

    val tvShows = listOf(

        TvShow(
            id = 1,
            name = "Lucifer",
            year = 2016,
            rating = 8.1,
            imageId = R.drawable.lucifer,
            overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
        ),
        TvShow(
            id = 2,
            name = "Ragnarok",
            year = 2020,
            rating = 7.5,
            imageId = R.drawable.ragnarok,
            overview = "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time."
        ),
        TvShow(
            id = 3,
            name = "The Flash",
            year = 2014,
            rating = 7.7,
            imageId = R.drawable.flash,
            overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
        ),
        TvShow(
            id = 4,
            name = "The Good Doctor",
            year = 2017,
            rating = 7.1,
            imageId = R.drawable.doctor,
            overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives"
        ),
        TvShow(
            id = 5,
            name = "Grey's Anatomy",
            year = 2005,
            rating = 7.5,
            imageId = R.drawable.anatomy,
            overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
        ),
        TvShow(
            id = 6,
            name = "The Walking Dead",
            year = 2010,
            rating = 8.2,
            imageId = R.drawable.twd,
            overview = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way."
        ),
        TvShow(
            id = 7,
            name = "Carnival Row",
            year = 2019,
            rating = 7.9,
            imageId = R.drawable.carnival,
            overview = "In a mystical and dark city filled with humans, fairies and other creatures, a police detective investigates a series of gruesome murders leveled against the fairy population. During his investigation, the detective becomes the prime suspect and must find the real killer to clear his name."
        ),
        TvShow(
            id = 8,
            name = "Legacies",
            year = 2018,
            rating = 7.4,
            imageId = R.drawable.legacies,
            overview = "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted."
        )

    )
}    
```
</details>

우선 이미지를 담을 `Composable` 함수를 작성합니다. 
```kotlin 
@Composable
private fun TvShowImage(tvShow: TvShow) {
    Image(
        painter = painterResource(id = tvShow.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .height(140.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
    )
}
```

그리고 이미지와 설명을 담을 레이아웃을 `Row`와 `Column`을 이용해서 작성합니다. 
```kotlin
@Composable
fun TvShowListItem(tvShow: TvShow, selectedItem: (TvShow) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable {
                    selectedItem(tvShow)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            TvShowImage(tvShow = tvShow)
            Column {
                Text( text = tvShow.name, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tvShow.overview,
                    style = MaterialTheme.typography.body1,
                    maxLines = 3, 
                    overflow = TextOverflow.Ellipsis 
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = tvShow.year.toString(),
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = tvShow.rating.toString(),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}
```
함수에서는 출력할 `TvShow`클래스와 ItemClickListener로 받을 람다함수를 파라메터로 넘겨 받습니다. 

그리고 `LazyColumn`으로 표시할 `Composable` 함수 내에서 `itemContent`값에 `TvShowList` 값을 넣습니다.
```kotlin
@Composable
fun DisplayingTvShows(selectedItem: (TvShow) -> Unit) {
    val tvShows = remember {
        TvShowList.tvShows
    }
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            items = tvShows,
            itemContent = {
                TvShowListItem(tvShow = it, selectedItem)
            }
        )
    }
}
```

마지막으로 `OnCreate` 함수에서 클릭 리스너와 함께 함수를 호출합니다. 
```kotlin 
DisplayingTvShows {
    Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
}
```

![image](https://user-images.githubusercontent.com/55622345/170053448-25f3f1fa-5b5e-4020-bb59-c4285a8e98e8.png)


## Ref. 
https://developer.android.com/jetpack/compose/gestures#scrolling <br>
