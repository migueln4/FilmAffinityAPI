package com.jjimenez.filmaffinity.exception;

import java.io.IOException;

/**
 * Define the movie not found
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
public class MovieNotFoundException extends IOException {

	private static final long serialVersionUID = 1L;

	/**
     * Constructs an {@code MovieNotFoundException} with {@code null}
     * as its error detail message.
     */
	public MovieNotFoundException() {
		super();
	}

	/**
     * Constructs an {@code MovieNotFoundException} with the specified detail message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
	public MovieNotFoundException(final String message) {
		super(message);
	}

	 /**
     * Constructs an {@code MovieNotFoundException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     *
     */
	public MovieNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	 /**
     * Constructs an {@code MovieNotFoundException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     *
     */
	public MovieNotFoundException(final Throwable cause){
		super(cause);
	}

}
