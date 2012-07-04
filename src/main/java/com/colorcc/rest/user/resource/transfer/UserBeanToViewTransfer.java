package com.colorcc.rest.user.resource.transfer;

import javax.inject.Named;

import com.colorcc.rest.user.bean.UserBean;
import com.colorcc.rest.user.resource.view.UserView;

@Named(value = "userBeanToViewTransfer")
public class UserBeanToViewTransfer implements Transfer<UserBean, UserView> {

	@Override
	public UserView transferTypetoBean(UserBean userBean, Object... objects) {
		if (userBean == null) {
			return null;
		}

		UserView userView = new UserView();
		userView.setId(userBean.getId());
		userView.setEmail(userBean.getEmail());
		userView.setRegTime(userBean.getRegisterTime());
		userView.setStatus(userBean.getStatus());
		return userView;
	}

	@Override
	public UserBean transferBeanToType(UserView userView, Object... objects) {
		return null;
	}
}