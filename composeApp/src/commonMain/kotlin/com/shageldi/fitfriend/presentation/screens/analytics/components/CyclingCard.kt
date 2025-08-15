package com.shageldi.fitfriend.presentation.screens.analytics.components

@Composable
fun CyclingCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2C2C)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsBike,
                    contentDescription = "Cycling",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Cycling",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            // Simple route visualization
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF404040))
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Draw a simple route path
                    val path = listOf(
                        Offset(size.width * 0.2f, size.height * 0.7f),
                        Offset(size.width * 0.4f, size.height * 0.3f),
                        Offset(size.width * 0.6f, size.height * 0.5f),
                        Offset(size.width * 0.8f, size.height * 0.2f)
                    )

                    for (i in 0 until path.size - 1) {
                        drawLine(
                            color = Color(0xFF8BC34A),
                            start = path[i],
                            end = path[i + 1],
                            strokeWidth = 4.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
        }
    }
}