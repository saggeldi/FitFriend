package com.shageldi.fitfriend.presentation.screens.analytics.components

@Composable
fun HeartRateCard() {
    val metrics = listOf(
        MetricCard(
            title = "Heart Rate",
            value = "79 Bpm",
            icon = Icons.Default.Favorite,
            backgroundColor = Color(0xFFFFCDD2),
            iconColor = Color(0xFFE91E63)
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = metrics[0].backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = metrics[0].icon,
                    contentDescription = metrics[0].title,
                    tint = metrics[0].iconColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = metrics[0].title,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Heart rate wave visualization
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                val waveHeight = 20.dp.toPx()
                val waveLength = size.width / 8

                for (i in 0..15) {
                    val x = i * waveLength / 2
                    val height = if (i % 4 == 1) waveHeight * 1.5f
                    else if (i % 4 == 2) waveHeight * 0.5f
                    else waveHeight

                    drawLine(
                        color = Color(0xFFE91E63),
                        start = Offset(x, size.height),
                        end = Offset(x, size.height - height),
                        strokeWidth = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }

            Text(
                text = metrics[0].value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
