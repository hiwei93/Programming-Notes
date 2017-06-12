package com.interview.file;

public class ExceptionFileNotExist extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExceptionFileNotExist (String err) {
		super(err);
	}

}
