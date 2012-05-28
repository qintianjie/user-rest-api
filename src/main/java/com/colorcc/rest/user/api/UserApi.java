package com.colorcc.rest.user.api;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.colorcc.rest.user.bean.UserBean;
import com.colorcc.rest.user.resource.UserResource;
import com.colorcc.rest.user.resource.dto.UserBeanToViewDto;
import com.colorcc.rest.user.resource.view.UserView;
import com.colorcc.rest.user.service.UserService;

/**
 * <p>
 * GET : /user/{id}, get user by id.
 * </p>
 * 
 * <p>
 * POST : /user, create a user.
 * </p>
 * 
 * <p>
 * PUT : /user, modify a user.
 * </p>
 * 
 * <p>
 * DELETE : /user/{id}, delete user by id.
 * </p>
 * 
 * <p>
 * GET: /user?email=XXX, get user by email.
 * </p>
 * 
 * 
 * @author qtj
 * @version 2012-05-27
 * 
 */

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

	/**
	 * { "status": 0, "email": "jack0@colorcc.com", "passwd": "pwd", "registerTime": "May 27, 2012 12:00:00 AM", "salt": "salt" }
	 * 
	 * @param userBean
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(UserBean userBean) {
		if (logger.isInfoEnabled()) {
			logger.info("Create a user who's email is " + userBean.getEmail());
		}
		userServiceImpl.createUser(userBean);
		return Response.ok().build();
	}

	/**
	 * { "id" : 2, "status": 0, "email": "jack22222@colorcc.com", "passwd": "pwd", "registerTime": "May 27, 2012 12:00:00 AM", "salt": "salt" }
	 * 
	 * @param userBean
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifyUser(UserBean userBean) {
		if (userBean == null) {
			return null;
		}
		if (logger.isInfoEnabled()) {
			logger.info("Update a user who id is " + userBean.getId());
		}

		userServiceImpl.update(userBean);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id) {
		if (logger.isInfoEnabled()) {
			logger.info("Delete user who's id is " + id);
		}
		userServiceImpl.deleteUser(id);
		return Response.ok().build();
	}

	/**
	 * http://localhost:8083/user?email=jack5@colorcc.com
	 * 
	 * @param email
	 * @return
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUserByEmail(@QueryParam("email") String email) {
		if (email == null) {
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Search user who's email is " + email);
		}

		UserBean userBean = userServiceImpl.loadUserByEmail(email);

		UserResource userResource = new UserResource();
		UserView userView = userBeanToViewDto.transferTypetoBean(userBean);
		userResource.setBaseObject(userView);

		return Response.ok().entity(userResource).build();
	}

}
