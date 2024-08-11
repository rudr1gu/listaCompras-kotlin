package com.example.listadecompras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listadecompras.ui.theme.ListadecomprasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListadecomprasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListaComprasScreen()
                }
            }
        }
    }
}

@Composable
fun ListaComprasScreen() {
    var produto by remember { mutableStateOf("") }
    val listaCompras = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = produto,
            onValueChange = { produto = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                if (produto.isNotBlank()) {
                    listaCompras.add(produto)
                    produto = ""
                }
            }) {
                Text("Adicionar")
            }

            Button(onClick = {
                listaCompras.remove(produto)
                produto = ""
            }) {
                Text("Remover")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Lista de Compras:")

        if (listaCompras.isEmpty()) {
            Text("Nenhum item na lista")
        } else {
            Column {
                listaCompras.forEach { item ->
                    Text(text = item)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListadecomprasTheme {
        ListaComprasScreen()
    }
}