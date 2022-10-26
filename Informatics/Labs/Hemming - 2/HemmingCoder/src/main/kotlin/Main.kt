import kotlin.math.pow
import kotlin.math.abs

fun main(args: Array<String>) {

    println("0 - Кодировать bin")
    println("1 - Декодировать и исправить bin по хеммингу")

    when (readLine()!!.trim()) {
        "0" -> coder()
        "1" -> decoder()
    }
}

fun coder() {
    println("Введите битовую последовательность")
    val bytes = readLine()!!.trim()
    println(Coder(bytes).encode().toString())
}

fun decoder() {
    println("Введите закодированную последовательность")
    val raw = readLine()!!.trim()

    var bytes = raw

    if (bytes.isNotBlank()) {

        println("Ввод корректен")
        println("Парсинг строки...")

        var control_r = ""

        var s = 0
        var index = 0 // 0(1), 1(2), 3(4) ...

        while (index < bytes.length - 1) {
            control_r += bytes[index].toString()
            val arr = bytes.toCharArray()
            arr[index] = '-'
            bytes = String(arr)
            s++
            index = 2.0.pow(s).toInt() - 1
        }

        val info_r: String = bytes.filter { it != '-' }

        printBits(info_r, control_r)

        println("Перерасчёт...")
        println(Coder(info_r).encode().controlSequence)

        if (Coder(info_r).encode().str == raw) {
            println("В исходной последовательности нет ошибок")
        } else {
            println("Ошибка")
            Coder.repair(raw, true)
        }

    }

    println()
    main(arrayOf())

}

private fun printBits(info: String, control: String) {
    print("Информационные биты исходные: ")

    info.toCharArray().forEachIndexed { index, c ->
        print("i$index = $c;  ")
    }

    print("\nКонтрольные биты исходные: ")

    control.toCharArray().forEachIndexed { index, c ->
        print("r$index = $c;  ")
    }

    println()

}

operator fun String.set(index: Int, value: String): String {
    val chars = this.toCharArray()
    chars[index] = value[0]
    this.chars()
    return String(chars)
}

fun String.insert(index: Int, string: String): String {
    return this.substring(0, index) + string + this.substring(index)
}

fun String.invert(): String {
    return this.toCharArray().reversedArray().concatToString()
}

class Coder(private val bits: String) {
    fun encode(): HemmingResult {

        var str = bits

        var s = 0
        var index = 0 // 0(1), 1(2), 3(4) ...

        while (index < str.length - 1) {
            str = str.insert(index, "-")

            s++
            index = 2.0.pow(s).toInt() - 1
        }

        //println(str)

        s = 0
        while (str.count { it == '-' } > 0) {

            val bitpow = 2.0.pow(s).toInt()

            var sum = 0

            for (i in bitpow until str.length + 1 step bitpow * 2) {
                val x: String = if (bitpow == 1) {
                    str[i - 1].toString()
                } else {
                    try {
                        str.substring(i - 1, i + bitpow - 1)
                    } catch (e: StringIndexOutOfBoundsException) {
                        str.substring(i - 1)
                    }
                }
                sum += x.count { it == '1' }
            }
            str = str.replaceFirst("-", (sum % 2).toString())
            s++
        }

        return HemmingResult(str)

    }

    data class HemmingResult(
        var str: String
    ) {
        var infoSequence: String
        var controlSequence: String = ""

        init { // делим на контрольную и информационную последовательности
            var result = str
            var s = 0
            var index = 0 // 0(1), 1(2), 3(4) ...

            while (index < result.length - 1) {
                controlSequence += result[index].toString()
                val q = result.toCharArray()
                q[index] = '-'
                result = String(q)
                s++
                index = 2.0.pow(s).toInt() - 1
            }

            infoSequence = result.filter { it != '-' }
        }
    }

    companion object {
        private fun getInfo(str: String): String {
            var result = str
            var s = 0
            var index = 0 // 0(1), 1(2), 3(4) ...
            var controlSequence = ""
            var infoSequence = ""

            while (index < result.length - 1) {
                controlSequence += result[index].toString()
                val q = result.toCharArray()
                q[index] = '-'
                result = String(q)
                s++
                index = 2.0.pow(s).toInt() - 1
            }

            infoSequence = result.filter { it != '-' }
            return infoSequence
        }

        fun repair(raw: String, logging: Boolean = false): HemmingResult {
            val res =  HemmingResult(raw)
            val raw_control = res.controlSequence
            var syndrom = ""
            Coder(res.infoSequence).encode().controlSequence.toCharArray().forEachIndexed { index, c ->
                syndrom += if (c != raw_control[index])
                    "1"
                else
                    "0"
            }
            val err_index = (syndrom.invert()).toInt(2) - 1
            if (logging) {
                println("Синдром: $syndrom")
                println("Индекс ошибки: $err_index")
            }
            var new = res.str
            val q = new.toCharArray()
            q[err_index] = if (q[err_index] == '1') '0' else '1'
            new = q.concatToString()

            println("Исправленная полная последовательность: $new")
            println("Исходная посылка: ${getInfo(new)}")

            return Coder(new).encode()
        }
    }

}


