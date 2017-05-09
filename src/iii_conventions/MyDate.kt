package iii_conventions

import java.time.Month

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(date: MyDate): Int {
        if (this.year.compareTo(date.year) == 0) {
            if (this.month.compareTo(date.month) == 0) {
                if (this.dayOfMonth.compareTo(date.dayOfMonth) == 0) {
                    return 0
                }else{
                    return this.dayOfMonth.compareTo(date.dayOfMonth)
                }
            } else {
                return this.month.compareTo(date.month)
            }
        }
        return this.year.compareTo(date.year)
    }
}

operator fun MyDate.plus(b: MyDate) = MyDate(this.year + b.year, this.month + b.month, this.dayOfMonth + b.dayOfMonth)


operator fun MyDate.plus(b: TimeInterval) = when (b) {
    TimeInterval.DAY -> MyDate(this.year, this.month, this.dayOfMonth + 1)
    TimeInterval.WEEK -> MyDate(this.year + 1, this.month, this.dayOfMonth)
    TimeInterval.YEAR -> MyDate(this.year + 1, this.month, this.dayOfMonth)
    else -> MyDate(this.year, this.month, this.dayOfMonth)

}


operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator {
        return Iterator
    }

    companion object Iterator : kotlin.collections.Iterator<MyDate> {
        override fun next(): MyDate {
            if (this.hasNext()) {

            }
            return MyDate(1, 1, 1)
        }

        override fun hasNext(): Boolean {
            return false;
        }
    }

}

operator fun DateRange.contains(other: MyDate): Boolean {
    return other.compareTo(start) in 0..1 && other.compareTo(endInclusive) in -1..0
}
