package com.colorcc.rest.user.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.colorcc.rest.user.bean.UserBean;
import com.colorcc.rest.user.resource.UserResource;
import com.colorcc.rest.user.resource.UsersResource;
import com.colorcc.rest.user.resource.dto.UserBeanToViewDto;
import com.colorcc.rest.user.resource.view.UserView;
import com.colorcc.rest.user.service.UserService;

/**
 * <p>
 * GET: /users?startRow=XXX&fetchSize=XXX, get page users.
 * </p>
 * 
 * @author qtj
 * @version 2012-05-28
 * 
 */
@Named
@Singleton
@Path("/users")
public class UsersApi {

	private static final Logger logger = LoggerFactory.getLogger(UsersApi.class);

	@Resource(name = "userServiceImpl")
	UserService userServiceImpl;

	@Resource(name = "userBeanToViewDto")
	UserBeanToViewDto userBeanToViewDto;

	/**
	 * http://localhost:8083/users?startRow=0&fetchSize=5
	 * 
	 * @param startRow
	 * @param fetchSize
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("startRow") int startRow, @QueryParam("fetchSize") int fetchSize) {
		if (logger.isDebugEnabled()) {
			logger.debug("Find users by from line " + startRow + " to " + (startRow + fetchSize));
		}

		if (fetchSize > 1000) {
			logger.warn("Query too many recores, that's may cause low performance.");
		}

		List<UserBean> userBeans = userServiceImpl.findUser(startRow, fetchSize);

		List<UserResource> userResourceList = new ArrayList<UserResource>();
		for (UserBean userBean : userBeans) {
			UserResource userResource = new UserResource();
			UserView userView = userBeanToViewDto.transferTypetoBean(userBean);
			userResource.setBaseObject(userView);
			userResourceList.add(userResource);
		}
		UsersResource usersResource = new UsersResource();
		usersResource.setBaseObject(userResourceList);
		return Response.ok().entity(usersResource).build();
	}

}
