package com.cooperlighting.xyzdemotraining.data

data class Rating(var rate: Double = 0.0, var count: Int = 0){
    override fun toString(): String {
        return "$rate $count"
    }
}