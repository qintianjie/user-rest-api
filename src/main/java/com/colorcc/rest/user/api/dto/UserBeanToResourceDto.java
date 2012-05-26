package com.colorcc.rest.user.api.dto;

import javax.inject.Named;

import com.colorcc.rest.user.bean.UserBean;
import com.colorcc.rest.user.resource.UserResource;

@Named(value="userBeanToResourceDto")
public class UserBeanToResourceDto implements BaseDto<UserBean, UserResource> {

	@Override
	public UserResource transferTypetoBean(UserBean userBean, Object... objects) {
		if (userBean == null) {
			return null;
		}
		UserResource userResource = new UserResource();
		userResource.setId(userBean.getId());
		userResource.setEmail(userBean.getEmail());
		userResource.setRegTime(userBean.getRegisterTime());
		userResource.setStatus(userBean.getStatus());
		return userResource;
	}

	@Override
	public UserBean transferBeanToType(UserResource userResource, Object... objects) {
		return null;
	}

}
