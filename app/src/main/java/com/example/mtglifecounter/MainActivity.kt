package com.example.mtglifecounter


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import com.example.mtglifecounter.ui.theme.MTGLifeCounterTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.Data.Player
import com.example.mtglifecounter.ViewModel.GameViewModel


class MainActivity : ComponentActivity() {
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MTGLifeCounterTheme {
                ScreenSplitInFour(gameViewModel)
            }
        }
    }
}

@Composable
fun PlayerLifeCounter( //Función de contaje de vidas
    player: Player,
    modifier: Modifier = Modifier
) {

    //Valores inmutables que se muestran en la partida
    var name by rememberSaveable { mutableStateOf(player.name) }
    var life by rememberSaveable { mutableStateOf(player.life) }

    Box(modifier = modifier) {

        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            /*BasicTextField*/

            BasicTextField( //Nombre modificable
                value = name,
                onValueChange = {
                    name = it
                    player.name =
                        it //asignamos el valor de name (recogido en la variable) al jugador
                },
                textStyle = LocalTextStyle.current.copy( //asignamos estilo al nombre con color y demás
                    color = Color.White,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier //le damos estilo al BasicTextField
                    .background(Color.Transparent)
                    .padding(4.dp)
            )

            /*BOTONES + VIDA*/

            Spacer(modifier = Modifier.height(8.dp))

            /*Botón decrementar*/

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {  //Boton decrecer
                    life--
                    player.life = life //Actualizamos la vida del jugador
                }) {
                    Text(text = "-")
                }
                Spacer(modifier = Modifier.width(8.dp))

                /*Vida*/

                Text(
                    life.toString(), //convertimos la vida, actualizable, en un String
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White, //¿Añadir función para que cambie de color?
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                /*Boton incrementar*/

                Button(onClick = {
                    life++
                    player.life = life
                }) {
                    Text("+")
                }
            }
        }
    }
}

/*Función para partir la pantalla*/

@Composable
fun ScreenSplitInFour(gameViewModel: GameViewModel) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) { //Estructura partida

            Column( //Dividimos en DOS columnas
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f) //nos aseguramnos de compensar el peso en todas las secciones
                    .fillMaxHeight()
            ) {

                /*PLAYER 1*/
                PlayerLifeCounter(
                    player = gameViewModel.players[0],
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .rotate(180f)
                )

                /*PLAYER 2*/
                PlayerLifeCounter( //añadimos el contador para el jugador 1
                    player = gameViewModel.players[1],
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Blue)

                )
            }

            //Ya tenemos la mitad de la pantalla así que añadimos la segunda mitad

            Column( //segunda columna
                modifier = Modifier
                    .weight(1f) //nos aseguramnos de compensar el peso en todas las secciones
                    .fillMaxHeight()

            ) {

                /*PLAYER 3*/
                PlayerLifeCounter( //añadimos el contador para el jugador 1
                    player = gameViewModel.players[3],
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .rotate(180f)
                        .background(Color.Green)
                )

                /*PLAYER 4*/
                PlayerLifeCounter( //añadimos el contador para el jugador 1
                    player = gameViewModel.players[3],
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Red)

                )

            }

        }

    }




