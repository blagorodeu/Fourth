package com.example.fourth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fourth.ui.theme.FourthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FourthTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UI_Work(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UI_Work(modifier: Modifier = Modifier)
{
    val input = remember{mutableStateOf("")}
    val res_sum = remember{mutableStateOf("")}
    val res_addend = remember{mutableStateOf("")}
    val res_iterations = remember{mutableStateOf("")}

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Число x", modifier.padding(8.dp))
            TextField(
                value = input.value,
                onValueChange = { newText -> input.value = newText }
            )
        }
        Button(onClick = { },
            shape = RectangleShape,     // прямоугольная кнопка
            modifier = Modifier.padding(8.dp)
        ){ Text("OK") }
        Text("Сумма: " + res_sum.value)
        Text("Последнее слагаемое: " + res_addend.value, modifier.padding(3.dp))
        Text("Количество итераций: " + res_iterations.value)
    }
}

@Preview(showBackground = true)
@Composable
fun FourthPreview() {
    FourthTheme {
        UI_Work()
    }
}