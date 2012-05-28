package com.colorcc.rest.user.resource;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsersResource extends Resources<UserResource> {

	@Override
	@JsonProperty(value="users")
	public List<UserResource> getBaseObject() {
		return this.baseObject;
	}

	@Override
	public void setBaseObject(List<UserResource> baseObject) {
		this.baseObject = baseObject;
	}


}
