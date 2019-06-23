package io.github.xshadov.easyrandom.vavr.exception;

import java.lang.reflect.Type;

public class GenericParameterNotComparableException extends RuntimeException {
	public GenericParameterNotComparableException(final Type type) {
		super("Type does not implement Comparable: " + type.getTypeName());
	}
}
