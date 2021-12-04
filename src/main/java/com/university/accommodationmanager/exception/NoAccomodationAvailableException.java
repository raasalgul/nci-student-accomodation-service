package com.university.accommodationmanager.exception;

import lombok.extern.slf4j.Slf4j;

public class NoAccomodationAvailableException extends RuntimeException {
	public NoAccomodationAvailableException(String message) {
		super(message);
	}

}
