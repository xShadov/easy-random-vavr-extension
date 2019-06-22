package io.github.xshadov.easyrandom.vavr.exception;

public class GenericParameterNotComparableException extends RuntimeException {
	public GenericParameterNotComparableException(final String type) {
		super("Type does not implement Comparable: " + type);
	}
}
