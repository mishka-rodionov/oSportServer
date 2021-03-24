package data.models

data class FinishListItem(
        val id: String,
        val userId: String,
        val startTime: Float,
        val finishTime: Float,
        val resultTime: Float,
        val absolutePlace: Int,
        val placeInCategory: Int,
        val description: String
)