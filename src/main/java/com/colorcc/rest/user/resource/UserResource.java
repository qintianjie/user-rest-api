package com.colorcc.rest.user.resource;

import com.colorcc.rest.user.resource.view.UserView;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResource extends Resource<UserView> {

	@Override
	public void setBaseObject(UserView baseObject) {
		this.baseObject = baseObject;
	}

	@Override
	@JsonProperty(value="user")
	public UserView getBaseObject() {
		return this.baseObject;
	}
}
