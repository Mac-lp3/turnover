package org.mac.sim.exception;

public class TurnoverException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4588033715675571830L;

	public TurnoverException(final String message) {
		super(message);
	}

	public TurnoverException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
