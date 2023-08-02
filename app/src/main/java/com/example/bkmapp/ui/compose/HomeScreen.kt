package com.example.bkmapp.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bkmapp.R

data class NavButton(
    val route: String,
    val text: String,
    val idIcon: Int? = R.drawable.ic_dummy
)

@Composable
fun HomeScreen(navController: NavController){

    val gridSize = 3
    val buttonSize = 80

    val navButtonCashIn = listOf(
        NavButton("UnderConstruction","Angsuran Pinjaman KSM"),
        NavButton("UnderConstruction","Ekuitas"),
        NavButton("UnderConstruction","Pemasukan Lainnya")
    )
    val navButtonCashOut = listOf(
        NavButton("UnderConstruction","Realisasi Pinjaman KSM"),
        NavButton("UnderConstruction","Penarikan BOP KSM"),
        NavButton("UnderConstruction","Biaya Operasional"),
        NavButton("UnderConstruction","Biaya Lingkungan"),
        NavButton("UnderConstruction","Biaya Sosial"),
        NavButton("UnderConstruction","Anggaran Tahunan"),
    )
    val navButtonBank = listOf(
        NavButton("UnderConstruction","Setoran"),
        NavButton("UnderConstruction","Penarikan"),
        NavButton("UnderConstruction","Bunga"),
        NavButton("UnderConstruction","Biaya Bank")
    )
    val navButtonReport = listOf(
        NavButton("UnderConstruction","Kas"),
        NavButton("UnderConstruction","Bank"),
        NavButton("UnderConstruction","Anggaran"),
        NavButton("UnderConstruction","Angsuran"),
        NavButton("UnderConstruction","Kolektibilitas"),
        NavButton("UnderConstruction","Laba/Rugi"),
        NavButton("UnderConstruction","BBNS"),
    )

    LazyColumn(Modifier.fillMaxSize()) {
        item {

            GridTitle(title = "Laporan", iconId = R.drawable.ic_report)
            GridContent( navController, gridSize , buttonSize, navButtonReport )

            GridTitle(title = "Kas Masuk", iconId = R.drawable.ic_cash_out)
            GridContent( navController, gridSize , buttonSize, navButtonCashIn )

            GridTitle(title = "Kas Keluar", iconId = R.drawable.ic_cash_out)
            GridContent( navController, gridSize , buttonSize, navButtonCashOut )

            GridTitle(title = "Buku Bank", iconId = R.drawable.ic_dollar_circle)
            GridContent( navController, gridSize , buttonSize, navButtonBank )
        }
    }
}

@Composable
fun GridContent(navController: NavController, gridSize: Int, buttonSize: Int, navButton: List<NavButton>){
    val maxHeight = buttonSize * 5
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        userScrollEnabled = false,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = maxHeight.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ){
        items(navButton.size) { index ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .size(buttonSize.dp)
                        .aspectRatio(1f),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { navController.navigate(navButton[index].route) }
                ) {
                    navButton[index].idIcon?.let {
                        Icon(
                            painter = painterResource(it),
                            contentDescription = "Icon ${navButton[index].text}",
                            modifier = Modifier
                                .size(30.dp)
                                .aspectRatio(1f)
                        )
                    }
                }
                Text(
                    text = navButton[index].text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun GridTitle(title: String, iconId: Int){
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = "Icon $title",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Divider(
            thickness = 1.2.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}