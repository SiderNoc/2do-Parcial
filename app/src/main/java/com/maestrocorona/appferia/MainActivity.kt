package com.maestrocorona.appferia

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(
                onNavigateToSecondActivity = {
                    // Navegar a Activity2
                    startActivity(Intent(this, Activity2::class.java))
                },// Navegar a DetalleNaveActivity
                onNavigateToDetalleNave = { naveName, imageResource ->
                    // pasa los datos a DetalleNaveActivity
                    val intent = Intent(this, DetalleNaveActivity::class.java).apply {
                        putExtra("NAVE_NAME", naveName)
                        putExtra("IMAGE_RESOURCE", imageResource)
                    }
                    startActivity(intent)
                },// Navegar a AtraccionesActivity
                onNavigateToAtracciones = {
                    startActivity(Intent(this, AtraccionesActivity::class.java))
                }
            )
        }
    }
}

@Composable
fun MainScreen(
    onNavigateToSecondActivity: () -> Unit,
    onNavigateToDetalleNave: (String, Int) -> Unit,
    onNavigateToAtracciones: () -> Unit
) {
    // Main screen
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {//titulo
            Text(
                text = "FERIA TABASCO\n2025",
                style = TextStyle(
                    color = colorResource(id = R.color.purple_40),
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start), // Alineamos el contenido del Text a la izquierda
                textAlign = TextAlign.Left
            )
            // Business items con su nombre, imagen y onNavigate a la actividad correspondiente
            BusinessItem(
                text = "Negocios de la Nave 1",
                imageResource = R.drawable.imagen_nave_1,
                onNavigate = { onNavigateToDetalleNave("Negocios de la Nave 1", R.drawable.imagen_nave_1) }
            )
            BusinessItem(
                text = "Negocios de la Nave 2",
                imageResource = R.drawable.imagen_nave_2,
                onNavigate = { onNavigateToDetalleNave("Negocios de la Nave 2", R.drawable.imagen_nave_2) }
            )
            BusinessItem(
                text = "Negocios de la Nave 3",
                imageResource = R.drawable.imagen_nave_3,
                onNavigate = { onNavigateToDetalleNave("Negocios de la Nave 3", R.drawable.imagen_nave_3) }
            )
            BusinessItem(
                text = "Atracciones y Conciertos",
                imageResource = R.drawable.imagen_conciertos,
                onNavigate = { onNavigateToAtracciones() }//Este es el botonAtracciones diferente a las otras cards
            )

            // Boton para navegar a la Activity2
            Button(
                onClick = onNavigateToSecondActivity,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Fechas importantes")
            }
        }
    }
}

@Composable
fun BusinessItem(text: String, imageResource: Int, onNavigate: () -> Unit) {
    // Card reutilizable
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.purple_80)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Imagen de la nave
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Imagen de $text",
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            // Texto de la card
            Text(
                text = text,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.purple_40)
                )
            )
            // Botón "Ver más"
            FilledTonalButton(onClick = onNavigate,
                modifier = Modifier.padding(end = 8.dp)) {
                Text("Ver más")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MainScreen(onNavigateToSecondActivity = { /* nada */ },
        onNavigateToDetalleNave = { _, _ -> /* nada */ },
        onNavigateToAtracciones = { /* nada */ })
}