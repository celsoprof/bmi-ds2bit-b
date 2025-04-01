package br.senai.sp.jandira.bmi_ds2bitb.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.bmi_ds2bitb.R
import br.senai.sp.jandira.bmi_ds2bitb.calcs.bmiCalculator
import br.senai.sp.jandira.bmi_ds2bitb.model.BmiStatus
import br.senai.sp.jandira.bmi_ds2bitb.screens.components.BmiLevel
import br.senai.sp.jandira.bmi_ds2bitb.utils.isFilled
import br.senai.sp.jandira.bmi_ds2bitb.utils.numberFormat
import java.util.Locale

@Composable
fun BMIResultScreen(navController: NavHostController?) {

    val context = LocalContext.current

    val sharedUserFile = context
        .getSharedPreferences("usuario", Context.MODE_PRIVATE)

    val age = sharedUserFile.getInt("user_age", 0)
    val height = sharedUserFile.getInt("user_height", 0).toDouble()
    val weight = sharedUserFile.getInt("user_weight", 0)

    val bmiResult = bmiCalculator(weight, height)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFCDDC39),
                        Color(0xFFFF5722)
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.your_result),
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp
                ),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(130.dp),
                        colors = CardDefaults
                            .cardColors(containerColor = Color.White),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 4.dp,
                            color = bmiResult.color
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = String.format(
                                    Locale.getDefault(),
                                    "%.1f",
                                    bmiResult.bmi.second),
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        text = bmiResult.bmi.first,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                    Card(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp)
                            .fillMaxWidth()
                            .height(80.dp),
                        colors = CardDefaults
                            .cardColors(
                                containerColor = Color.LightGray
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.age)
                                )
                                Text(
                                    text = "$age",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            VerticalDivider(
                                modifier = Modifier
                                    .fillMaxHeight()
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.weight)
                                )
                                Text(
                                    text = "$weight Kg",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            VerticalDivider(modifier = Modifier.fillMaxHeight())
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.label_height)
                                )
                                Text(
                                    text = String.format(
                                        Locale.getDefault(),
                                        "%.2f",
                                        height.div(100)
                                    ),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    // Mostrar o resultado do IMC
                    BmiLevel(
                        markColor = colorResource(R.color.light_blue),
                        text1 = stringResource(R.string.under_weight_table),
                        text2 = "< ${numberFormat(18.5)}",
                        isFilled = isFilled(bmiResult.bmiStatus, BmiStatus.UNDERWEIGHT)
                    )
                    BmiLevel(
                        markColor = colorResource(R.color.light_green),
                        text1 = stringResource(R.string.normal_weight_table),
                        text2 = "${numberFormat(18.6)} - ${numberFormat(24.9)}",
                        isFilled = isFilled(bmiResult.bmiStatus, BmiStatus.NORMAL)
                    )
                    BmiLevel(
                        markColor = colorResource(R.color.yellow),
                        text1 = stringResource(R.string.over_weight_table),
                        text2 = "${numberFormat(25.0)} - ${numberFormat(29.9)}",
                        isFilled = isFilled(bmiResult.bmiStatus, BmiStatus.OVERWEIGHT)
                    )
                    BmiLevel(
                        markColor = colorResource(R.color.light_orange),
                        text1 = stringResource(R.string.obesity1),
                        text2 = "${numberFormat(30.0)} - ${numberFormat(34.9)}",
                        isFilled = isFilled(bmiResult.bmiStatus, BmiStatus.OBESITY1)
                    )
                    BmiLevel(
                        markColor = colorResource(R.color.dark_orange),
                        text1 = stringResource(R.string.obesity2),
                        text2 = "${numberFormat(35.0)} - ${numberFormat(39.9)}",
                        isFilled = isFilled(bmiResult.bmiStatus, BmiStatus.OBESITY2)
                    )
                    BmiLevel(
                        markColor = colorResource(R.color.red),
                        text1 = stringResource(R.string.obesity1),
                        text2 = "> ${numberFormat(39.9)}",
                        isFilled = isFilled(bmiResult.bmiStatus, BmiStatus.OBESITY3)
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.new_calculate)
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun BMIResultScreenPreview() {
    BMIResultScreen(null)
}