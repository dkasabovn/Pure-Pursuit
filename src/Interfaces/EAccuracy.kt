package Interfaces

enum class EAccuracy private constructor(var size: Double) {
    SAMPLE_ROBLOX(100.toDouble()),
    SAMPLE_LOW(1000.toDouble()),
    SAMPLE_HIGH(10000.toDouble()),
    SAMPLE_OVERHEAT(100000.toDouble()),
    SAMPLE_WTF(1000000.toDouble())
}
