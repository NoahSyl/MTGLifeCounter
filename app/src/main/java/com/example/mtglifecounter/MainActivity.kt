package com.example.mtglifecounter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.Components.Buttons
import com.example.mtglifecounter.Components.bounceClick
import com.example.mtglifecounter.Data.Player
import com.example.mtglifecounter.ViewModel.GameViewModel
import com.example.mtglifecounter.ui.theme.MTGLifeCounterTheme
import com.example.mtglifecounter.ui.theme.customTextStyle


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
    gameViewModel: GameViewModel,
    player: Player,
    backgroundID: Int,
    rotateAngle: Float = 0f,
    modifier: Modifier = Modifier
) {


    //Valores inmutables que se muestran en la partida
    var name by rememberSaveable { mutableStateOf(player.name) }
    var life by rememberSaveable { mutableStateOf(player.life) }

    Box(
        modifier = modifier
            .rotate(rotateAngle) // Aplica rotación al contenedor
            .fillMaxSize()
            .border(0.3.dp, Color.White)
    ) {

        // Imagen de fondo
        Image(
            painter = painterResource(id = backgroundID),
            contentDescription = "Background image",
            modifier = Modifier
                .fillMaxSize()
                .rotate(0f),
            contentScale = ContentScale.Crop // Ajustar imagen

        )


        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            /*BasicTextField*/

            BasicTextField(
                //Nombre modificable
                value = name,
                onValueChange = {
                    name = it
                    player.name =
                        it //asignamos el valor de name (recogido en la variable) al jugador
                },
                textStyle = customTextStyle.copy( //asignamos estilo al nombre con color y demás
                    color = Color.White,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                modifier = Modifier //le damos estilo al BasicTextField
                    .background(Color.Transparent)
                    .padding(4.dp),

                )

            /*BOTONES + VIDA*/

            Spacer(modifier = Modifier.height(8.dp))

            /*Botón decrementar*/

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Buttons(
                    onShortClick = {
                        gameViewModel.decrementLife(player)
                        life = player.life
                    },
                    onLongPressAction = {
                        gameViewModel.decrementLife(player)
                        life = player.life
                    },
                    iconID = R.drawable.botonminus,
                    contentDescription = "Decrement life",
                    modifier = Modifier
                )

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
                        .bounceClick(),
                    style = customTextStyle.copy(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                /*Boton incrementar*/

                Buttons(
                    onShortClick = {
                        gameViewModel.incrementLife(player)
                        life = player.life
                    },
                    onLongPressAction = {
                        gameViewModel.incrementLife(player)
                        life = player.life
                    },
                    iconID = R.drawable.botonplus,
                    contentDescription = "Increment life",
                    modifier = Modifier
                )
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
        modifier = Modifier.fillMaxSize()
    ) { //Estructura partida

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
                gameViewModel = gameViewModel,
                backgroundID = R.drawable.background6,
                rotateAngle = 180f,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()


            )

            /*PLAYER 2*/
            PlayerLifeCounter( //añadimos el contador para el jugador 1
                player = gameViewModel.players[1],
                gameViewModel = gameViewModel,
                backgroundID = R.drawable.background8,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()

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
                player = gameViewModel.players[2],
                gameViewModel = gameViewModel,
                backgroundID = R.drawable.background7,
                rotateAngle = 180f,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()


            )

            /*PLAYER 4*/
            PlayerLifeCounter( //añadimos el contador para el jugador 1
                player = gameViewModel.players[3],
                gameViewModel = gameViewModel,
                backgroundID = R.drawable.background4,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()


            )

        }

    }

}





