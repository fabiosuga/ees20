package br.com.suga.service;

import br.com.suga.business.PedidoBusiness;
import br.com.suga.entity.Pedido;
import br.com.suga.exception.ApiError;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("v1/pedido")
public class PedidoResource {


    @Inject
    PedidoBusiness business;

    @GET
    @Path("/cliente/{cpf}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response listar(@PathParam("cpf") String cpf) {
        List<Pedido> lst = null;
        try {
            lst = business.listarPorCpf(cpf);
        } catch (Exception e) {
            lst = new ArrayList<>(0);
        }

        return Response.ok().entity(lst).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/{id}")
    public Response obter(@PathParam("id") Integer id) {
        Pedido pedido = null;
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            pedido = business.obterPorId(id);
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }

        return Response.ok().entity(pedido).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response incluir(Pedido pedido) {
        if (pedido == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        URI location = null;
        try {
            business.salvar(pedido);
            location = new URI("v1/pedido/" + pedido.getId());
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }

        return Response.status(Response.Status.CREATED).contentLocation(location).build();
    }

//    @PUT
//    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    public Response alterar(Pedido pedido) {
//
//        return Response.ok().build();
//    }

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
}
