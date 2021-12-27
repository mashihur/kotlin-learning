package nicestring

fun String.isNice(): Boolean {
    var condition1 : Boolean = setOf<String>("bu", "ba", "be").none { it in this }
    var condition2 : Boolean = count { it in "aeiou" } >= 3
    var condition3 = false
    var temp : Char? = null

    for (i in this) {
        if (i == temp) {
            condition3 = true
            break
        }
        temp = i
    }

    var count = 0
    if (condition1) count++
    if (condition2) count++
    if (condition3) count++

    return count >= 2
}
//fun String.isNice(): Boolean { // Mt first approach
//    val vowels = listOf<Char>('a', 'e', 'i', 'o', 'u')
//    val badStrings = listOf("bu", "ba", "be")
//    var condition1 = true
//    var condition2 = false
//    var condition3 = false
//    var temp : Char = '1'
//    var vowelCount = 0
//
//
//    for (i in this) {
//
//        if (condition1) {
//            var subStr = "$temp$i"
//            if (badStrings.contains(subStr)) {
//                condition1 = false
//            }
//        }
//
//        if (vowels.contains(i)) {
//            vowelCount++
//        }
//
//        if (temp == i) {
//            condition3 = true;
//        }
//        temp = i
//    }
//    if (vowelCount >= 3) {
//        condition2 = true;
//    }
//
//    return ((condition1 && condition2) || (condition2 && condition3) || (condition3 && condition1))
//}