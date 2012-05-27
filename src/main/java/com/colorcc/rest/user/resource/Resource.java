package com.colorcc.rest.user.resource;

import java.net.URI;

public abstract class Resource<T> {
	
	private URI uri;
	
	protected T baseObject;
	
	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public abstract T getBaseObject();

	public abstract void setBaseObject(T baseObject);

}
