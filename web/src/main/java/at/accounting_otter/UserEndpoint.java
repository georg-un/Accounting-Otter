package at.accounting_otter;

import at.accounting_otter.entity.User;
import at.accounting_otter.rest.RestObjectMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1/user")
@RequestScoped
public class UserEndpoint {

    @Inject
    private UserService userService;

    @Inject
    private RestObjectMapper restMapper;

    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("user_id") int userId) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(restMapper.toRestUser(userService.getUser(userId)))
                    .build();
        } catch (ObjectNotFoundException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        return userService.createUser(user);
    }


}
