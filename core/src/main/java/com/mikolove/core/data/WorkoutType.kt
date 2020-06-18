package com.mikolove.core.data

enum class WorkoutType(var idWorkoutType: Long, var type: String) {
    TRAPS(1, "Traps"),
    DELT_FRONT(2, "Front Delt"),
    DELT_BACK(3, "Back Delt"),
    DELT_MIDDLE(4, "Middle Delt"),
    CHEST_UPPER(5, "Upper Chest"),
    CHEST_MIDDLE(6, "Middle Chest"),
    CHEST_LOWER(7, "Lower Chest"),
    BICEPS(8, "Biceps"),
    TRICEPS(9, "Triceps"),
    FOREARM(10, "Forearm"),
    LATS(11, "Lats"),
    BACK_UPPER(12, "Upper Back"),
    BACK_LOWER(13, "Lower Back"),
    ABS_CORE(14, "Core Abs"),
    ABS_OBLIQUE(15, "Oblique"),
    ABS_LOWER(16, "Lower Abs"),
    GLUTES(17, "Glutes"),
    LEGS(18, "Legs")
}