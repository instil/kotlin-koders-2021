package compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

import compose.Operation.*

@Composable
fun InstilTheme(content: @Composable () -> Unit) {
    val colors = MaterialTheme.colors.copy(primary = Color.LightGray)
    MaterialTheme(content = content, colors = colors)
}

@Composable
fun DisplayText(text: String) =
    Text(
        text = text,
        style = TextStyle(color = Color.Black, fontSize = 28.sp),
        modifier = Modifier.padding(all = 10.dp)
    )

@Composable
fun NumberButton(onClick: () -> Unit, number: Int) =
    Button(
        onClick = onClick,
        modifier = Modifier.padding(all = 5.dp)
    ) {
        Text(
            number.toString(),
            style = TextStyle(color = Color.Black, fontSize = 18.sp)
        )
    }

@Composable
fun OperationButton(onClick: () -> Unit, label: String) =
    Button(
        onClick = onClick,
        modifier = Modifier.padding(all = 2.dp)
    ) {
        Text(
            label,
            style = TextStyle(color = Color.Black, fontSize = 14.sp)
        )
    }

@Composable
fun OperationButtonColumn(
    onSelect: (Operation) -> Unit,
    onClear: () -> Unit,
    onEquals: () -> Unit
) = Column {
    listOf(
        { onSelect(Add) } to "+",
        { onSelect(Subtract) } to "-",
        { onSelect(Multiply) } to "*",
        { onSelect(Divide) } to "/",
        onClear to "Clear",
        onEquals to "="
    ).forEach {
        OperationButton(onClick = it.first, label = it.second)
    }
}

@Composable
fun NumberButtonRow(range: IntRange, onClick: (Int) -> Unit) = Row {
    range.forEach { num ->
        NumberButton(onClick = { onClick(num) }, number = num)
    }
}

@Composable
fun NumberButtonColumn(onClick: (Int) -> Unit) = Column {
    NumberButtonRow(1..3, onClick)
    NumberButtonRow(4..6, onClick)
    NumberButtonRow(7..9, onClick)
    NumberButtonRow(0..0, onClick)
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(width = 300.dp, height = 400.dp)
    ) {
        val savedTotal = remember { mutableStateOf(0) }
        val displayedTotal = remember { mutableStateOf(0) }
        val operationOngoing = remember { mutableStateOf(Operation.None) }
        val operationJustChanged = remember { mutableStateOf(false) }

        val anOperationIsOngoing = { operationOngoing.value != None }
        val updateSavedTotalFromDisplay = { savedTotal.value = displayedTotal.value }
        val resetOngoingOperation = { operationOngoing.value = None }
        val resetTotals = {
            displayedTotal.value = 0
            savedTotal.value = 0
        }
        val addDigit = { num: Int ->
            displayedTotal.value = (displayedTotal.value * 10) + num
        }

        val numberSelected = { num: Int ->
            when {
                operationJustChanged.value -> {
                    operationJustChanged.value = false
                    updateSavedTotalFromDisplay()
                    displayedTotal.value = num
                }
                displayedTotal.value == 0 -> displayedTotal.value = num
                else -> addDigit(num)
            }
        }

        val clearSelected = {
            resetTotals()
            resetOngoingOperation()
            operationJustChanged.value = false
        }

        val doOperation = {
            val saved = savedTotal.value
            val displayed = displayedTotal.value

            when (operationOngoing.value) {
                Add -> displayedTotal.value = saved + displayed
                Subtract -> displayedTotal.value = saved - displayed
                Multiply -> displayedTotal.value = saved * displayed
                Divide -> displayedTotal.value = saved / displayed
                else -> throw IllegalStateException("No operation to execute!")
            }
        }

        val operationSelected = { op: Operation ->
            operationJustChanged.value = true
            if (anOperationIsOngoing()) {
                doOperation()
                updateSavedTotalFromDisplay()
            }
            operationOngoing.value = op
        }

        val equalsSelected = {
            doOperation()
            resetOngoingOperation()
        }

        InstilTheme {
            Column {
                Row {
                    DisplayText(text = "${displayedTotal.value}")
                }
                Row {
                    NumberButtonColumn(numberSelected)
                    OperationButtonColumn(operationSelected, clearSelected, equalsSelected)
                }
            }
        }
    }
}
