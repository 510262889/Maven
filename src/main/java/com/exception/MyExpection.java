package com.exception;

public class MyExpection extends RuntimeException {
	private static final long serialVersionUID = -2780524204761062576L;

    public MyExpection() {
        super();
    }

    public MyExpection(String msg, Throwable t) {
        super( msg, t );
    }

    public MyExpection(String msg) {
        super( msg );
    }

    public MyExpection(Throwable t) {
        super( t );
    }
}
