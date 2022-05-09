# JetpackComposeFundamentals
[Jetpack Compose](https://developer.android.com/jetpack/compose/why-adopt)는 navtie UI를 쉽고 빠르게 작성할 수 있도록 도와주는 클래스입니다. <br>
jetpack compose는 기존에 XML에서 작성하던 UI를 Kotlin 코드로 작성함으로 아래와 같은 이점을 가져옵니다. 
- Less code
- Intuitive
- Accelerate develpoment
- Powerful

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
