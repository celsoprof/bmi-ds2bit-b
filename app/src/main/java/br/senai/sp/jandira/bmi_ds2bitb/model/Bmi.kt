package br.senai.sp.jandira.bmi_ds2bitb.model

import androidx.compose.ui.graphics.Color

data class Bmi(
    var bmi: Pair<String, Double>,
    var bmiStatus: BmiStatus = BmiStatus.NORMAL,
    var color: Color = Color(0xFF009688)
)
