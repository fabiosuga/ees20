package br.com.suga.service;

import br.com.suga.business.ProdutoBusiness;
import br.com.suga.entity.Cliente;
import br.com.suga.entity.Produto;
import br.com.suga.exception.ApiError;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("v1/produto")
public class ProdutoResource {

    @Inject
    ProdutoBusiness business;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response listar() {
        List<Produto> lstProduto = new ArrayList<>();
        try {
            lstProduto = business.listarTodos();
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }
        return Response.ok().entity(lstProduto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response incluir(@Valid Produto produto) {
        if (produto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        URI location = null;
        try {
            business.salvar(produto);
            location = new URI("v1/produto/" + produto.getId());
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }

        return Response.status(Response.Status.CREATED).entity(produto).contentLocation(location).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/{id}")
    public Response alterar(@PathParam("id") Integer id, @Valid Produto produto) {
        if (produto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            business.salvar(produto);
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }

        return Response.status(Response.Status.OK).entity(produto).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response excluir(@PathParam("id") Integer id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            business.excluir(id);
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response obter(@PathParam("id") Integer id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Produto produto = null;
        try {
            produto = business.obter(id);
        } catch (Exception e) {
            ApiError me = new ApiError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
            return Response.serverError().entity(me).build();
        }
        return Response.ok().entity(produto).build();
    }
}
