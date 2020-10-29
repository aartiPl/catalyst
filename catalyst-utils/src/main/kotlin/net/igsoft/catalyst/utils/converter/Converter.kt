package net.igsoft.catalyst.utils.converter

import java.util.function.Function

fun interface Converter<FROM, TO> : Function<FROM, TO> {
    override fun apply(input: FROM): TO
}
