package io.github.mat3e.springtricks.m1;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Locale;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/countries")
public class CountryController {
    @Inject
    private CountryService service;

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountry(@PathParam("code") String code, @QueryParam("locale") Locale locale) {
        if (locale == null) {
            return toResponse(service.findByCode(code));
        }
        return toResponse(service.findDetailedByCodeAndLocale(code, locale));
    }

    private Response toResponse(Optional<?> entity) {
        return entity.map(Response::ok)
                .orElse(Response.status(NOT_FOUND))
                .build();
    }
}
