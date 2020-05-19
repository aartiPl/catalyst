/*
  Author: Marcin Kuszczak
  Redistributions of source code must retain the above authorship notice
 */

package net.igsoft.catalyst.utils.converter;

import java.util.function.Function;

public interface Converter<FROM, TO> extends Function<FROM, TO> {

    @Override
    TO apply(FROM input);
}
