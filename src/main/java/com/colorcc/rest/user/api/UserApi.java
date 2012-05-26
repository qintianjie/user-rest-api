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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.colorcc.rest.user.api.dto.UserBeanToResourceDto;
import com.colorcc.rest.user.bean.UserBean;
import com.colorcc.rest.user.resource.UserResource;
import com.colorcc.rest.user.service.UserService;


@Named
@Singleton
@Path("/user")
public class UserApi {
	
	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

	
	@Context
	ServletContext sc;
	
	@Resource(name="userServiceImpl")
	UserService userServiceImpl;
	
	@Resource(name="userBeanToResourceDto")
	UserBeanToResourceDto userBeanToResourceDto;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") int id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Find user by id " + id);
		}
		
		UserBean userBean = userServiceImpl.loadUser(id);
		
		UserResource userResource = userBeanToResourceDto.transferTypetoBean(userBean);
		return Response.ok().entity(userResource).build();
	}
	
	@GET
	@Path("/{startRows}/{fetchSize}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUsers(@PathParam("startRows") int startRows, @PathParam("fetchSize") int fetchSize) {
		if (logger.isDebugEnabled()) {
			logger.debug("Find user list");
		}
		
		List<UserBean> userList = userServiceImpl.findUser(startRows, fetchSize);
		return Response.ok().entity(userList).build();
	}

}
