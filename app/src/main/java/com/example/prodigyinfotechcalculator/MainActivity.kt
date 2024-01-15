package com.example.prodigyinfotechcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prodigyinfotechcalculator.commons.ButtonSize
import com.example.prodigyinfotechcalculator.commons.operationBtnColor
import com.example.prodigyinfotechcalculator.commons.operationBtnTextColor
import com.example.prodigyinfotechcalculator.ui.theme.ProdigyInfotechCalculatorTheme
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProdigyInfotechCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CalculatorScreen() {
    var TypedEquation = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp, 4.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp, 12.dp, 6.dp, 12.dp)
                .fillMaxHeight(0.3f),
            contentAlignment = Alignment.BottomEnd

        ) {

            Text(
                text = TypedEquation.value,
                modifier = Modifier.padding(6.dp),
                fontFamily = FontFamily.Default,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.displayMedium
            )

            IconButton(
                onClick = {
                    if (TypedEquation.value.isEmpty())
                        return@IconButton
                    TypedEquation.value =
                        TypedEquation.value.substring(0, TypedEquation.value.length - 1)
                },
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .size(40.dp)
            ) {
                Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = null)
            }

        }


        Row(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(10.dp, 0.dp)
                .background(color = Color.Gray)
        ) {}
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp, 2.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceAround, modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp)
                ) {
                    Button(
                        onClick = { TypedEquation.value = "" },
                        modifier = Modifier
                            .background(
                                color = operationBtnColor(),
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = operationBtnColor(),
                            contentColor = operationBtnTextColor()
                        )
                    ) {
                        Text(text = "C", color = operationBtnTextColor())
                    }
                    Button(
                        onClick = {
                            if (TypedEquation.value.isEmpty() || TypedEquation.value.last() == '(' ||
                                arrayOf('/', '*', '-', '+').contains(TypedEquation.value.last())
                            )
                                return@Button
                            var j = TypedEquation.value.length - 1
                            while (j != 0) {
                                if (TypedEquation.value[j] == '(') {
                                    TypedEquation.value += ')'
                                    return@Button
                                }
                                j -= 1
                            }

                            TypedEquation.value += '('

                        }, modifier = Modifier
                            .background(
                                color = operationBtnColor(),
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = operationBtnColor()
                        )
                    ) {
                        Text(text = "( )")
                    }
                    Button(
                        onClick = {

                        }, modifier = Modifier
                            .background(
                                color = operationBtnColor(),
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = operationBtnColor(),
                            contentColor = operationBtnTextColor()
                        )
                    ) {
                        Text(text = "%")
                    }
                    Button(
                        onClick = {
                            if (TypedEquation.value.isEmpty())
                                return@Button
                            if (!arrayOf('-', '+', '/', '*').contains(TypedEquation.value.last()))
                                TypedEquation.value += '/'
                        }, modifier = Modifier
                            .background(
                                color = operationBtnColor(),
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = operationBtnColor(),
                            contentColor = operationBtnTextColor()
                        )
                    ) {
                        Text(text = "/")
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceAround, modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp)
                ) {
                    Button(
                        onClick = {
                            TypedEquation.value += 7
                        },
                        modifier = Modifier
                            .background(
                                color = operationBtnColor(),
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "7")
                    }
                    Button(
                        onClick = {
                            TypedEquation.value += 8
                        }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "8")
                    }
                    Button(
                        onClick = {
                            TypedEquation.value += 9
                        }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "9")
                    }
                    Button(
                        onClick = {
                            if (TypedEquation.value.isEmpty())
                                return@Button
                            if (!arrayOf('-', '+', '/', '*').contains(TypedEquation.value.last()))
                                TypedEquation.value += '*'
                        }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = operationBtnColor(),
                            contentColor = operationBtnTextColor()
                        )
                    ) {
                        Text(text = "x")
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceAround, modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp)
                ) {
                    Button(
                        onClick = {
                            TypedEquation.value += 4
                        },
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "4")
                    }
                    Button(
                        onClick = {
                            TypedEquation.value += 5
                        }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "5")
                    }
                    Button(
                        onClick = { TypedEquation.value += 6 }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "6")
                    }
                    Button(
                        onClick = {
                            if (TypedEquation.value.isEmpty())
                                return@Button
                            if (!arrayOf('-', '+', '/', '*').contains(TypedEquation.value.last()))
                                TypedEquation.value += '+'
                        }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = operationBtnColor(),
                            contentColor = operationBtnTextColor()
                        )
                    ) {
                        Text(text = "+")
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceAround, modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp)
                ) {
                    Button(
                        onClick = { TypedEquation.value += 1 },
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "1")
                    }
                    Button(
                        onClick = { TypedEquation.value += 2 }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "2")
                    }
                    Button(
                        onClick = { TypedEquation.value += 3 }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "3")
                    }
                    Button(
                        onClick = {
                            if (TypedEquation.value.isEmpty())
                                return@Button
                            if (!arrayOf('-', '+', '/', '*').contains(TypedEquation.value.last()))
                                TypedEquation.value += '-'
                        }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = operationBtnColor(),
                            contentColor = operationBtnTextColor()
                        )
                    ) {
                        Text(text = "-")
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceAround, modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp)
                ) {
                    Button(
                        onClick = {
                            if (TypedEquation.value.isEmpty())
                                return@Button
                            var j = TypedEquation.value.length - 1
                            var targetNum = ""
                            while (j != 0 && !arrayOf('/', '*', '-', '+', '(', ')').contains(
                                    TypedEquation.value[j]
                                )
                            ) {
                                targetNum = TypedEquation.value[j] + targetNum
                                j -= 1
                            }
                            targetNum = "-$targetNum"
                            TypedEquation.value =
                                TypedEquation.value.substring(0, j + 1) + targetNum
                        },
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = operationBtnColor(),
                            contentColor = operationBtnTextColor()
                        )
                    ) {
                        Text(text = "+/-")
                    }
                    Button(
                        onClick = { TypedEquation.value += 0 }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = "0")
                    }
                    Button(
                        onClick = {
                            if (TypedEquation.value.isEmpty())
                                return@Button
                            var j = TypedEquation.value.length - 1
                            while (j != 0) {
                                if (TypedEquation.value[j] == '.')
                                    return@Button
                                else if (arrayOf('/', '*', '-', '+', '(', ')').contains(
                                        TypedEquation.value[j]
                                    )
                                ) {
                                    break
                                }
                                j -= 1
                            }

                            TypedEquation.value += '.'


                        }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize)
                    ) {
                        Text(text = ".")
                    }
                    Button(
                        onClick = {
                            if (TypedEquation.value.isEmpty())
                                return@Button
                            val exp = ExpressionBuilder(TypedEquation.value).build()
                            TypedEquation.value = exp.evaluate().toString()
                        }, modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(80.dp)
                            )
                            .size(ButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Text(text = "=")
                    }
                }
            }
        }


    }
}