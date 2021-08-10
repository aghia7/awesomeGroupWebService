package controllers;

import dtos.UserInputDTO;
import dtos.UserOutputDTO;
import entities.User;
import repositories.interfaces.IUserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("users")
public class UserController {

    @Inject
    private IUserRepository userRepo;


    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserInputDTO userDto) {

        User foundUser = userRepo.getUserByUsername(userDto.getUsername());

        if (foundUser != null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Username is already in use!")
                    .build();
        }

        User user = new User(
                userDto.getName(),
                userDto.getSurname(),
                userDto.getUsername(),
                userDto.getGender()
        );

        boolean created = userRepo.addUser(user);
        if (!created) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("User cannot be created!")
                    .build();
        }

        return Response.status(Response.Status.CREATED)
                .entity("User created successfully!").build();
    }


    @GET
    public Response getAllUsers() {
        List<User> users = userRepo.getAll();
        List<UserOutputDTO> usersOutput = new LinkedList<>();
        for (User user : users) {
            UserOutputDTO userOutput = new UserOutputDTO(
                    user.getName(),
                    user.getSurname(),
                    user.getGender()
            );
            usersOutput.add(userOutput);
        }
        return Response.ok(usersOutput).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int id) {
        User user = userRepo.getUser(id);
        if (user != null) {
            return Response.ok(user).build();
        }

        return Response.status(Response.Status.NO_CONTENT)
                .entity("User with such id does not exist!").build();
    }
}
