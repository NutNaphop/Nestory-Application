package com.naphop.nestory.data.local

object EmojiProvider {
    val emojiCategories = mapOf(
        "Common" to listOf("📦", "🏠", "🎁", "⭐", "📍", "📂", "📝", "🏷️"),
        "Food & Drink" to listOf("🍎", "🥛", "🍞", "🥩", "🥦", "🍳", "🥤", "🍕", "🍰", "🍚"),
        "Household" to listOf("🧼", "🧹", "🧺", "🛋️", "💡", "🔌", "🌡️", "🧴", "🧻", "🧯"),
        "Tools" to listOf("🔨", "🔧", "🔩", "📏", "🔋", "✂️", "🎨", "🧵", "🧶"),
        "Health" to listOf("💊", "🩹", "🧪", "🌡️", "🦷", "👓")
    )

    val allEmojis = emojiCategories.values.flatten()
    
    const val DEFAULT_EMOJI = "📦"
}
