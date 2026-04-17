package com.example.a207419_lishangyu_izwan_lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a207419_lishangyu_izwan_lab2.ui.theme.A207419_LiShangYu_Izwan_Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A207419_LiShangYu_Izwan_Lab3Theme {
                StepBloomScreen()
            }
        }
    }
}

@Composable
fun StepBloomScreen() {
    var stepInput by remember { mutableStateOf("") }
    var currentSteps by remember { mutableStateOf("0") }
    var calories by remember { mutableStateOf("0.0") }
    var distance by remember { mutableStateOf("0.00") }
    var isExpanded by remember { mutableStateOf(false) }

    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(20.dp)
            .navigationBarsPadding()
    ) {
        Text(
            text = "Today",
            style = MaterialTheme.typography.headlineLarge,
            color = colorScheme.onBackground
        )

        Text(
            text = "Matric No: A207419",
            style = MaterialTheme.typography.bodyMedium,
            color = colorScheme.onBackground,
            modifier = Modifier.padding(top = 6.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = colorScheme.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = currentSteps,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onPrimary
                )

                Text(
                    text = "/6000 Steps",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.onPrimary
                )

                Text(
                    text = "Daily average: $currentSteps",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        OutlinedTextField(
            value = stepInput,
            onValueChange = { stepInput = it },
            label = { Text("Enter your steps") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Button(
            onClick = {
                currentSteps = stepInput.ifEmpty { "0" }

                val steps = stepInput.toIntOrNull() ?: 0
                val calValue = steps * 0.04
                val distValue = steps * 0.0008

                calories = "%.1f".format(calValue)
                distance = "%.2f".format(distValue)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Update Progress")
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .clickable { isExpanded = !isExpanded }
                .animateContentSize(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = colorScheme.primaryContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Exercise",
                    style = MaterialTheme.typography.headlineMedium,
                    color = colorScheme.onPrimaryContainer
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "0m",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Min",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorScheme.onPrimaryContainer
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = calories,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Kcal",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorScheme.onPrimaryContainer
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = distance,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Km",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorScheme.onPrimaryContainer
                        )
                    }
                }

                if (isExpanded) {
                    Text(
                        text = "Goal: 6000 steps per day",
                        modifier = Modifier.padding(top = 20.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = "This card expands with a simple animation when clicked.",
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = "Tap again to collapse the card.",
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = colorScheme.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Activity",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.onPrimary
                )
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.onPrimary
                )
            }
        }
    }
}