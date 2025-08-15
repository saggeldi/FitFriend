package com.shageldi.fitfriend.presentation.screens.analytics.components

@Composable
fun SleepCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E4F3)),
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
                    imageVector = Icons.Default.Bedtime,
                    contentDescription = "Sleep",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sleep",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sleep bar chart
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                val sleepData = listOf(0.6f, 0.4f, 0.8f, 0.3f, 0.7f, 0.9f, 0.5f)

                sleepData.forEach { height ->
                    Box(
                        modifier = Modifier
                            .width(12.dp)
                            .height((60 * height).dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF673AB7))
                    )
                }
            }
        }
    }
}