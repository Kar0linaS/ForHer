import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.R
import com.dziubi.forher.data.UiState
import com.dziubi.forher.samples.samplePayment

@Composable
fun PaymentScreen(
    data: UiState.Payment,
    onPaymentClick: () -> Unit = {}
) {

    val scrollState = rememberScrollState()

    val roundedDouble by remember{
        val totalAmount = data.basketList.sumOf { it.product.price.toDouble() * it.count }
        val rounded = String.format("%.2f", totalAmount)
        mutableStateOf(rounded)
    }
    Column(modifier = Modifier
        .verticalScroll(scrollState)) {

        PaymentMethod()
        Delivery()
        Payment(totalAmount = roundedDouble,
            onPaymentClick = onPaymentClick)

    }
}
@Composable
fun PaymentMethod(){

    val image = ImageBitmap.imageResource(id = R.drawable.ic_visa_logo)

    Column(){
        Text(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 30.dp),
            text = "Metoda płatności",
            color = Color.Gray,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Surface(){
            Row(){
                Image(modifier = Modifier
                    .padding(start = 16.dp)
                    .size(50.dp, 50.dp),
                    bitmap= image,
                    contentDescription = null)

                Text(modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                    text = " Metoda płatności",
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun Delivery() {

    Column(){
        Text(modifier = Modifier.padding(horizontal = 16.dp, vertical= 30.dp),
            text = "Sposób dostawy",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.Gray
        )
    }

}
@Composable
fun Payment(
    totalAmount: String,
    onPaymentClick: () -> Unit = {}
){

    Column(modifier = Modifier
        .padding(start = 16.dp, top = 100.dp, bottom = 20.dp, end = 16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,

    ){
        Row(modifier = Modifier
            .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 30.dp),
                text = "Koszt",
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(modifier = Modifier
                .weight(1f)
                .padding(start = 30.dp, end = 16.dp),
                text = totalAmount,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                fontSize = 20.sp
            )
        }
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = onPaymentClick ,
            shape = RoundedCornerShape(5),

        ) {
            Text(
                text = "Zapłać",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PaymentScreenPreview(){
    PaymentScreen(samplePayment)
}