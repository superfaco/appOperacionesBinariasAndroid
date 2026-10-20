package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun fib(n: Int): Int {
    if(n == 0 || n == 1) {
        return 1;
    }
    return fib(n - 1) + fib(n - 2)
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column() {
        OperacionBinaria()
        OperacionBinaria(onClickHandler = {x,y -> x * y})
        OperacionBinaria(onClickHandler = {x,y -> x - y})
        OperacionBinaria(onClickHandler = {x,y -> x / y})
        OperacionBinaria(onClickHandler = {x,y -> fib(x) + fib(y)})
        OperacionBinaria(onClickHandler = {x,y -> fib(x) * fib(y)})
    }
}

@Composable
fun OperacionBinaria(onClickHandler: (Int, Int) -> Int = { x, y -> x + y }) {
    var x by remember{ mutableStateOf("") }
    var y by remember{ mutableStateOf("") }
    var resultado by remember{ mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        TextField(
            modifier = Modifier.width(50.dp),
            value = x,
            onValueChange = {x = it},
            label = {
                Text("x: ")
            }
        )
        TextField(
            modifier = Modifier.width(50.dp),
            value = y,
            onValueChange = {y = it},
            label = {
                Text("y: ")
            }
        )
        Button(
            onClick = {
                resultado = try {
                    onClickHandler(x.toInt(), y.toInt()).toString()
                } catch (e: Exception) {
                    "Error"
                }
            }
        ) { Text("Calcular") }
        Text("Resultado: $resultado")
    }
}

@Composable
@Preview(showBackground = true)
fun OperacionBinariaPreview() {
    MyApplicationTheme() {
        OperacionBinaria()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting()
    }
}