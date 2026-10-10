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
import androidx.compose.foundation.layout.width
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
import kotlin.math.pow

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
    val input_X = remember{mutableStateOf("")}
    val input_num = remember{mutableStateOf("")}
    val res_sum = remember{mutableStateOf("")}
    val res_addend = remember{mutableStateOf("")}
    val res_iterations = remember{mutableStateOf("")}

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text("(1/x)-(1/3*x^3)+(1/5*x^5)-(1/7*x^7)+(1/9*x^9)-...", modifier.padding(8.dp))
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Число x", modifier.padding(8.dp))
            TextField(
                value = input_X.value,
                onValueChange = { newText -> input_X.value = newText },
                modifier = Modifier.width(200.dp)
            )
        }
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Заданное число", modifier.padding(8.dp))
            TextField(
                value = input_num.value,
                onValueChange = { newText -> input_num.value = newText },
                modifier = Modifier.width(200.dp)
            )
        }
        Button(onClick = {
            if ((input_X.value != "") and (input_num.value != ""))
            {
                var x: Double = input_X.value.toDouble()
                var num: Double = input_num.value.toDouble()
                var iterations = 0.0
                var j = 1.0
                var res = 0.0
                while (1/(j*x.pow(j)) >= num)
                {
                    res = res + ((-1.0).pow(iterations)*1/(j*x.pow(j)))
                    iterations = iterations + 1.0
                    j = j + 2.0
                }
                if (iterations == 0.0)
                {
                    res = 1/x
                }
                res_sum.value = res.toString()
                res_addend.value = "1/(" + j.toInt().toString() + " * " + x.toString() + "^" + j.toInt().toString() + ")"
                res_iterations.value = iterations.toInt().toString()
            }
        },
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