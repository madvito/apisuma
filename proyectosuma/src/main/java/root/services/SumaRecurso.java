/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root.services;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")

public class SumaRecurso {
    @GET
    @Path("/suma")
    public String getSumaQuery(@QueryParam("numeros") String numeros){
        String listaNumeros[]=numeros.split(",");
        Integer suma=0;
        for (int i=0;i<listaNumeros.length;i++){
            suma=suma+Integer.parseInt(listaNumeros[i]);
        }
        return "La suma de los numeros "+ numeros + ". Es: "+ suma.toString();
    }
    @POST
    @Path("/suma")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public String getSumapost(String body){
        JsonObject j=Json.createReader(new StringReader(body)).readObject();
        String listaNumeros[]=j.getString("numeros").split(",");
        Integer suma=0;
        for (int i=0;i<listaNumeros.length;i++){
            suma=suma+Integer.parseInt(listaNumeros[i]);
        }
        JsonObject jr=(JsonObject) Json.createObjectBuilder()
            .add("listanumeros", j.getString("numeros"))
            .add("resultadosuma", suma)
            .build();
        return jr.toString();
    }
    @GET
    @Path("nombre/{nombre: [a-z]+}")
    public String getNombre(@PathParam("nombre") String nombre){
        return nombre;
    }
}
