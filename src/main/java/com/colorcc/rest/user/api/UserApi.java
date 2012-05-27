package com.colorcc.rest.user.api;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.colorcc.rest.user.api.dto.UserBeanToViewDto;
import com.colorcc.rest.user.bean.UserBean;
import com.colorcc.rest.user.resource.UserResource;
import com.colorcc.rest.user.resource.view.UserView;
import com.colorcc.rest.user.service.UserService;

@Named
@Singleton
@Path("/user")
public class UserApi {

	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

	@Context
	ServletContext sc;

	@Resource(name = "userServiceImpl")
	UserService userServiceImpl;

	@Resource(name = "userBeanToViewDto")
	UserBeanToViewDto userBeanToViewDto;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") int id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Find user by id " + id);
		}

		UserBean userBean = userServiceImpl.loadUser(id);

		// // A sample to use [ServiceBean] director, if the attributes in JSON string is the same as [ServiceBean] parameters.
		// UserResource userResource = new UserResource();
		// userResource.setBaseObject(userBean);

		// Another sample use DTO transfer [ServiceBean] to JSON attribute, this case is suit for the attribute in JSON is changed from [ServiceBean] parameters.
		UserResource userResource = new UserResource();
		UserView userView = userBeanToViewDto.transferTypetoBean(userBean);
		userResource.setBaseObject(userView);

		return Response.ok().entity(userResource).build();
	}

	@GET
	@Path("/page")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUsers(@QueryParam("startRows") int startRows, @QueryParam("fetchSize") int fetchSize) {
		if (logger.isDebugEnabled()) {
			logger.debug("Find user list");
		}

		List<UserBean> userList = userServiceImpl.findUser(startRows, fetchSize);
		return Response.ok().entity(userList).build();
	}

}
