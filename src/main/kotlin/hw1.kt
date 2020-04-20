fun main() {

    val amount = 200 // стоимость текущей продажи
    val total = 11_000 // сумма предыдущих продаж

    println("Fee to pay: ${calculateFee(amount, total)}") // плата для обычного пользователя

    println("Fee to pay: ${calculateFee(amount, total, true)}") // плата для эксклюзивного пользователя
}


fun calculateFee(amount: Int, total: Int, exclusive: Boolean = false): Double {

    return when {
        total in 0..1_000 -> {
            amount.toDouble() / 100 * getPercent(30, exclusive)
        }
        total in 1_001..10_000 -> {
            amount.toDouble() / 100 * getPercent(25, exclusive)
        }
        total in 10_001..50_000 -> {
            amount.toDouble() / 100 * getPercent(20, exclusive)
        }
        total >= 50_001 -> {
            amount.toDouble() / 100 * getPercent(15, exclusive)
        }
        else -> 0.0
    }

}

fun getPercent(percent: Int, exclusive: Boolean): Int {

    return if (exclusive) {
        percent - 5
    } else {
        percent
    }
}

