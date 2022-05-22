# JetpackComposeFundamentals
[Jetpack Compose](https://developer.android.com/jetpack/compose/why-adopt)는 navtie UI를 쉽고 빠르게 작성할 수 있도록 도와주는 클래스입니다. <br>
jetpack compose는 기존에 XML에서 작성하던 UI를 Kotlin 코드로 작성함으로 아래와 같은 이점을 가져옵니다. 
- Less code
- Intuitive
- Accelerate develpoment
- Powerful

### Others 
 - [Jetpack Compose - Recycler View](https://github.com/K-Mose/JetpackComposeFundamentals/blob/master/app/src/main/java/com/kmose/jetpackcomposefundamentals/recyclerview/README.md)

## Basic 
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeFundamentalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeFundamentalsTheme {
        Greeting("Android")
    }
}
```
Jetpack Compose에서는 XML 대신 Kotlin 코드로 UI를 작성합니다. 위에서 `<TextVivew/>`를 사용하는 것을 `Text()`composable function으로 대신 사용합니다.<br>
`Greeting`과 같은 새로운 Composable function을 정의함으로 데이터를 받고 UI로 나타내는 함수를 만들 수 있습니다. 


`@Preview`어노테이션을 사용함으로 화면 내에서 코드 디자인을 미리볼 수 있습니다. 

![image](https://user-images.githubusercontent.com/55622345/167359133-b105299f-166e-48a4-b61a-2f53d097d748.png)

여기서는 `DefaultPreview`함수 속 `Geeting`메서드의 인자 값을 바꾸면 오른쪽 design 화면의 텍스트가 바로 바뀌게 됩니다. 

## Modifier And Parameters
일반적인 `<TextView/>`xml은 아래와 같습니다. 
```xml
<TextView
      android:layout_width="wrap_content"    
      android:layout_height="wrap_content"
      android:text="Hello World!"
      android:testSize="20sp"
      ……
/>
```
Composable function에서도 xml의 속성을 인자 값으로 넘겨줌으로써 해당 값들을 추가할 수 있습니다. 
```kotlin
    Text(
        text = "Hello $name!",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        textAlign = TextAlign.Center
    )
```

[Modifier](https://developer.android.com/jetpack/compose/tutorial#tut-step-5)는 size, layout등으로  composable function가 어떻게 보여질것인지 정의할 수 있고, clickable, zoomable, draggable, scrollable 등의 UI의 상호작용을 추가할 수 있습니다. 

Modifier를 추가할 떄는 Modifier에 추가되는 순서에 따라서 적용되는 UI가 달라집니다. 
예를들어 `border`를 추가한 후 `padding`을 추가한 것과 `padding`을 추가한 후 `border`를 추가한 UI는 다릅니다. 
전자는 `border`안에 `padding`이 들어가지만 후자는 `padding`이 적용된 후 `border`가 적용됩니다. 

![image](https://user-images.githubusercontent.com/55622345/167362632-f6493e5f-ba8f-4b5a-8278-3cc65d3b1975.png)

![image](https://user-images.githubusercontent.com/55622345/167362777-e94499f1-5f87-4b4d-98e1-6483df9ab4b4.png)

```kotlin
    Text(
        text = "Hello $name!",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = Color.Yellow)
            .padding(10.dp)
            .border(2.dp, color = Color.Green)
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.3f)
    )
```
`fillMaxWidth`와 `fillMaxHeight`로 화면 전체를 덮을 수도 있고, 인자 값을 `float`값으로 넘겨줌으로 화면에서 차지할 비율을 설정 할 수 있습니다. 


### Layout Arrangment
여러 compsable function을 같이 사용하면 기본 값으로 View들이 겹치게 됩니다. 
```kotlin
        setContent {
            Greeting("Gorgeous Android")
            Greeting("Awesome Kotlin")
            Greeting("Mose")
        }
```
![image](https://user-images.githubusercontent.com/55622345/167364600-da6ff77c-7ad3-47c6-af3e-c9247316017b.png)

Composable function을 배치하기 위한 [`Column`, `Row`와 `Box`](https://developer.android.com/jetpack/compose/tutorial#using-a-column)의 3가지 방법이 있습니다. 
`Column`은 수직으로 배치하고 `Row`는 수평으로 배치됩니다. 

### Column 
`Column`에 `Modifier`를 추가하여 수평과 수직으로 배치를 조정할 수 있습니다. <br>
`verticalArrangement`는  `Arrangement`값을 받아 `Column`내에서 수직으로 배치합니다. 배치 값은 **CSS**의 `justify-content`와 유사합니다. 
- Top
- Center
- Bottom 
- SpaceAround
- SpaceBetween
- SpaceEvenly

`horizontalAlignment`는 `Alignment`값을 받아 `Column`내에서 수평으로 배치합니다. 
- CenterHorizontally
- Start
- End

아래는 위 View를 수평과 수직 중앙에 배치한 코드 입니다. 
```kotlin
            Column (
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxSize()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Greeting("Gorgeous Android")
                Greeting("Awesome Kotlin")
                Greeting("Mose")
            }
```
![image](https://user-images.githubusercontent.com/55622345/167368302-5696dad5-0e61-4389-aeb3-e9ad5a17bf06.png)


### Row
`Column`을 `Row`로 변경하겠습니다. <br>
`Row`도 `Column`과 유사하게 수평과 수직의 배치를 조정할 수 있지만 `Row`는 기본이 수평배치가 됩니다. 

![image](https://user-images.githubusercontent.com/55622345/167369468-8b7ab9e6-16e3-4302-81bf-411c2dcfcb92.png)

그렇기 때문에 `Column`과는 반대로 `horizontal`은 `Arrangement`값을 받고
- Top
- Center
- Bottom 
- SpaceAround
- SpaceBetween
- SpaceEvenly

`vertical`은 `Alignment`값을 받습니다. 
- CenterHorizontally
- Start
- End

```kotlin
        setContent {
            Row (
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxSize()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Greeting("Android")
                Greeting("Kotlin")
                Greeting("Mose")
            }
        }
```

### Box
`Box`는 **HTML**에서 `<div>`와 비슷 합니다. 

작성된 코드에서 `Greeting`을 지우고 `Box`를 생성하기 위한 함수를 추가하겠습니다. 
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxExample1()
        }
    }
}

@Composable
fun BoxExample1() {
    Box(
        modifier = Modifier
            .background(color = Color.Green)
            .size(180.dp, 300.dp)
    ) {

    }
}

```
<img src="https://user-images.githubusercontent.com/55622345/167399425-0d0a8f37-85d6-48d8-85cf-abf0464e9aea.png" width="200px">

`BoxExample1`의 `Box`안에 또다른 박스와 텍스트뷰를 추가해봅시다. 
```kotlin
@Composable
fun BoxExample1() {
    Box(
        modifier = Modifier
            .background(color = Color.Green)
            .size(180.dp, 300.dp)
    ) {
        Box (
            modifier = Modifier
                .background(color = Color.Yellow)
                .size(120.dp, 100.dp)
        ) {

        }

        Text(
            text = "Hello Box!",
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .background(color = Color.White)
                .size(90.dp, 50.dp)
        )
    }
}
```
![image](https://user-images.githubusercontent.com/55622345/167400209-dc21063d-8607-4975-a46c-1f903ab6b141.png)

추가된 child view들은 `Box`에서 스택형식으로 쌓이게 됩니다. child view의 `Modifier`에 `Align`값을 추가하여 parent view 내에서의 위치를 조정할 수 있습니다. 

```kotlin 
        Box (
            modifier = Modifier
                .background(color = Color.Yellow)
                .size(120.dp, 100.dp)
                .align(Alignment.TopEnd)
        ) {

        }

        Text(
            text = "Hello Box!",
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .background(color = Color.White)
                .size(90.dp, 50.dp)
                .align(Alignment.BottomCenter)
        )
    }
```
![image](https://user-images.githubusercontent.com/55622345/167400778-e73dc3e8-a04f-437d-990d-ecfde19b5473.png)

`Box`는 TopEnd로 `Text`는 BottomCenter로 값을 넣으면 부모 객체 내에서 위치를 잡게 됩니다. 

자식 객체들은 아래와 같이 부모 `Box`내에서 `Align`값에 따라서 위치가 바뀌게 됩니다.
```kotlin
@Composable
fun BoxExample2() {
    Box (
        modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxSize()
    ){
        Text(
            text = "TopStart",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "TopCenter",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.TopCenter)
        )
        Text(
            text = "TopEnd",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.TopEnd)
        )
        Text(
            text = "CenterStart",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = "Center",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.Center)
        )
        Text(
            text = "CenterEnd",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.CenterEnd)
        )
        Text(
            text = "BtmStart",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.BottomStart)
        )
        Text(
            text = "BtmCenter",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        )
        Text(
            text = "BtmEnd",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.BottomEnd)
        )
    }
}
```
![image](https://user-images.githubusercontent.com/55622345/167402145-baf0bdba-9d4c-4412-9177-24e43e115871.png)


`Box`를 사용하면 다른 View 객체들과 적절한 배치를 통해서 쉽게 디자인을 할 수 있습니다.
```kotlin
@Composable
fun BoxExample3() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.sok),
            contentDescription = "속초"
        )
        Text(
            text = "속초 해수욕장",
            style = MaterialTheme.typography.h4,
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart)
        )
        ButtonDefaults(
            onClick = {},
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Gray
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .border(5.dp, Color.DarkGray, RectangleShape)
        ) {
            Text("Back To List")
        }
    }
}
```
![image](https://user-images.githubusercontent.com/55622345/167405732-ee792cc9-5dc2-4d9d-8db1-0b2337e4a331.png)


## Buttons 
Jetpack Compose로 버튼을 추가해봅시다. 초기 버튼은 아래와 같습니다. <br>
`Button(onClick = { /*TODO*/ }) {}` <br>
`onClick`은 버튼을 눌렀을 때 실행시킬 람다식 자체이며 버튼 속성 값 다음의 중괄호 안에 버튼을 채울 텍스트를 아래와 같이 설정할 수 있습니다. 
```kotlin
Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    }) {
        Text("Add To Cart")
    }
```

만약에 버튼 클릭을 Disable하게 하고싶다면 버튼에 `enabled`속성을 `false`로 변경하면 됩니다.
```kotlin 
Button(onClick = {
    Toast.makeText(context, "Clicked on Button2", Toast.LENGTH_SHORT).show()
                 },
    enabled = false
) {
    Text("Add To Cart")
}    
```
![image](https://user-images.githubusercontent.com/55622345/168221886-a47dad96-cea0-4088-a2b3-e2564bb6ce95.png)

<details>
<summary>Basic Button Customizing</summary>

아래와 같이 `shape, contentPadding, border, color`등으로 스타일을 변경할 수 있습니다. 
```kotlin
    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    },
        shape = CutCornerShape(10.dp),
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor =  Color.White
        )
    ) {
        Text("Add To Cart",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }
```
</details>


다른 스타일의 버튼인 `TextButton`과 `Outline`버튼을 추가해보겠습니다. 
`TextButton`은 배경색이 없이 텍스트만 보이는 버튼이고, `OutlineButton`은 바깥 선만 있는 버튼입니다. 

```kotlin
TextButton(onClick = { 
    Toast.makeText(context, "Clicked on TextButton", Toast.LENGTH_SHORT).show()
}) {
    Text("Add To Cart")
}

OutlinedButton(onClick = { 
    Toast.makeText(context, "Clicked on OutlineButton", Toast.LENGTH_SHORT).show()
}) {
    Text("Add To Cart")
}
```
![image](https://user-images.githubusercontent.com/55622345/168221993-9431eeb1-3aee-4494-acd5-d7c2102c73bf.png)

마지막으로 `IconButton`을 추가해 봅시다. 
`IconButton`은 버튼을 아이콘 모양으로 생성하여 홈, 다운로드, 새로고침 등의 버튼으로 활용할 수 있습니다. 
```kotlin
    IconButton(onClick = { 
        Toast.makeText(context, "Clicked on IconButton", Toast.LENGTH_SHORT).show()
    }) {
        Icon( Icons.Filled.Refresh,
            contentDescription = "Refresh Button",
            tint = Color.Green,
            modifier = Modifier.size(80.dp)
        )
    }
```
`Icon`에는 `painter`를 통하여 이미지를 넣거나 `imageVector`로 벡터 이미지를 넣을 수 있습니다. 

![image](https://user-images.githubusercontent.com/55622345/168224788-aa95f495-8175-4a72-81c1-09dda7bbbc69.png)


## Ref.
https://developer.android.com/jetpack/compose <br>
https://developer.android.com/jetpack/compose/tutorial <br>
https://developer.android.com/jetpack/compose/mental-model <br>

https://stackoverflow.com/questions/58743541/how-to-get-context-in-jetpack-compose <br>
