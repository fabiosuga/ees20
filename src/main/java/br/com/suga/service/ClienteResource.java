package br.com.suga.service;


import br.com.suga.business.ClienteBusiness;
import br.com.suga.entity.Cliente;
import br.com.suga.exception.ApiError;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("v1/cliente")
public class ClienteResource {

    @Inject
    ClienteBusiness business;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response listar() {
        List<Cliente> lst = null;
        try {
            lst = business.listarTodos();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        GenericEntity<List<Cliente>> obj = new GenericEntity<List<Cliente>>(lst) {};
        return Response.ok().entity(obj).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response incluir(@Valid Cliente cliente) {
        if (cliente == null) {
            ApiError me = new ApiError(Response.Status.BAD_REQUEST.getStatusCode(), "Objeto nulo");
            return Response.status(Response.Status.BAD_REQUEST).entity(me).build();
        }

        URI location = null;
        try {
            business.salvar(cliente);
            location = new URI("v1/cliente/" + cliente.getId());
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }

        return Response.status(Response.Status.CREATED).contentLocation(location).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response alterar(@Valid Cliente cliente) {
        if (cliente == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            business.salvar(cliente);
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/{id}")
    public Response excluir(@PathParam("id") Integer id) {
        try {
            business.excluir(id);
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/{id}")
    public Response obter(@PathParam("id") Integer id) {
        Cliente cliente = null;
        try {
            cliente = business.obter(id);
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }
        return Response.ok().entity(cliente).build();
    }
}
