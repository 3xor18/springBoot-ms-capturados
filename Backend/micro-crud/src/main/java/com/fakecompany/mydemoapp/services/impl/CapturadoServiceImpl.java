package com.fakecompany.mydemoapp.services.impl;

import com.fakecompany.mydemoapp.Repositories.CapturadoRepositoy;
import com.fakecompany.mydemoapp.dtos.Request.CapturadoDto;
import com.fakecompany.mydemoapp.dtos.Response.CamposResponse;
import com.fakecompany.mydemoapp.dtos.Response.EntrenadorVsEntrenador;
import com.fakecompany.mydemoapp.entities.Capturado;
import com.fakecompany.mydemoapp.entities.Entrenador;
import com.fakecompany.mydemoapp.entities.Pokemon;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import com.fakecompany.mydemoapp.services.CapturadoService;
import com.fakecompany.mydemoapp.services.EntrenadorService;
import com.fakecompany.mydemoapp.services.PokemonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
public class CapturadoServiceImpl implements CapturadoService {

    @Value("${soy.una.variable.url}")
    private  String URL_8081;

    private final CapturadoRepositoy capturadoRepositoy;

    private final EntrenadorService entrenadorService;

    private final PokemonService pokemonService;
    @Autowired
    private RestTemplate clientRest;

    public CapturadoServiceImpl(CapturadoRepositoy capturadoRepositoy, EntrenadorService entrenadorService, PokemonService pokemonService) {
        this.capturadoRepositoy = capturadoRepositoy;
        this.entrenadorService = entrenadorService;
        this.pokemonService = pokemonService;
    }

    public Capturado findById(Long id) throws AppNotFoundException {
        log.info("findById: {}", id);
        return capturadoRepositoy.findByIdOptional(id)
                .orElseThrow(() -> new AppNotFoundException("No Encontre capturados con id: " + id));
    }

    @Override
    public ResponseEntity<?> guardar(CapturadoDto dto) throws AppInternalServerErrorException, AppNotFoundException {
       try{
           //buscar Pokemon
           Pokemon pokemonBD=pokemonService.findById(dto.getIdPokemon());
           //Buscar Entrenador
           Entrenador entrenadorBD=entrenadorService.findById(dto.getIdEntrenador());
           //Buscar si el Pokemon ya se registro con el Entrenador
           Capturado enBD=capturadoRepositoy.buscarSiExisteEnEntrenador(dto.getIdEntrenador(), dto.getIdPokemon());


           if(enBD!=null){
               throw new AppNotFoundException("Ya existe un Pokemon: "+dto.getIdPokemon()+" con el entrenador: "+dto.getIdEntrenador());
           }

           //creamos la entidad
           Capturado noGuardado = new Capturado();
           noGuardado.setEntrenador(entrenadorBD);
           noGuardado.setPokemon(pokemonBD);
           //guardamos en BD
           Capturado guardado=capturadoRepositoy.save(noGuardado);
           return ResponseEntity.status(HttpStatus.OK).body(guardado);
       }
       catch (AppNotFoundException e){
           e.printStackTrace();
           throw new AppNotFoundException(e.getMessage());
       }
       catch (Exception e){
           e.printStackTrace();
           throw new AppInternalServerErrorException("Error al Guardar el capturado");
       }
    }

    @Override
    public ResponseEntity<?> buscarTodos() throws AppInternalServerErrorException {
        List<Capturado> capturados=capturadoRepositoy.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(capturados);
    }

    @Override
    public ResponseEntity<?> buscarPorId(Long id) throws AppNotFoundException, AppInternalServerErrorException {
        try{
            Capturado enBd=findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(enBd);
        }
        catch (AppNotFoundException e){
            e.printStackTrace();
            throw new AppInternalServerErrorException(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AppInternalServerErrorException("Error al Buscar el capturado por ID");
        }
    }

    @Override
    public ResponseEntity<?> actualizar(Long id, CapturadoDto dto) throws AppNotFoundException, AppInternalServerErrorException {
        try{

            //buscar Pokemon
            Pokemon pokemonBD=pokemonService.findById(dto.getIdPokemon());
            //Buscar Entrenador
            Entrenador entrenadorBD=entrenadorService.findById(dto.getIdEntrenador());
            //Buscar si el Pokemon ya se registro con el Entrenador
            Capturado enBD=capturadoRepositoy.buscarSiExisteEnEntrenador(dto.getIdEntrenador(), dto.getIdPokemon());

            if(enBD!=null){
                throw new AppNotFoundException("Ya existe un Pokemon: "+dto.getIdPokemon()+" con el entrenador: "+dto.getIdEntrenador());
            }

            enBD.setPokemon(pokemonBD);
            enBD.setEntrenador(entrenadorBD);

            Capturado editado=capturadoRepositoy.save(enBD);

            return ResponseEntity.status(HttpStatus.OK).body(editado);
        }
        catch (AppNotFoundException e){
            e.printStackTrace();
            throw new AppInternalServerErrorException(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AppInternalServerErrorException("Error al Buscar el capturado por ID");
        }
    }

    @Override
    public ResponseEntity<?> borrar(Long id) throws AppNotFoundException, AppInternalServerErrorException {
        try{
            findById(id);
            capturadoRepositoy.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Capturado borrado");
        }
        catch (AppNotFoundException e){
            e.printStackTrace();
            throw new AppInternalServerErrorException(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AppInternalServerErrorException("Error al Eliminar el capturado por ID");
        }
    }

    @Override
    public ResponseEntity<?> buscarCampos() {

        ResponseEntity<CamposResponse> response = clientRest.exchange(
                URL_8081, HttpMethod.GET, null, CamposResponse.class
        );

        CamposResponse entity= response.getBody();
        entity.setNombre("Este va a ser el nuevo Nombre");

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @Override
    public ResponseEntity<?> crearDuelo(Long idEntrenador1, Long idEntrenador2) throws AppInternalServerErrorException {
        try{


            //Buscar Entrenador
            Entrenador entrenador1=entrenadorService.findById(idEntrenador1);
            Entrenador entrenador2=entrenadorService.findById(idEntrenador2);
            CamposResponse campoDeBatalla= buscarCampo();

            EntrenadorVsEntrenador vs=new EntrenadorVsEntrenador();
            vs.setEntrenador1(entrenador1);
            vs.setEntrenador2(entrenador2);
            vs.setCampoDeBatalla(campoDeBatalla);


            return ResponseEntity.status(HttpStatus.OK).body(vs);
        }
        catch (AppNotFoundException e){
            e.printStackTrace();
            throw new AppInternalServerErrorException(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AppInternalServerErrorException("Error al crear Duelo");
        }
    }


    private CamposResponse buscarCampo(){

        ResponseEntity<CamposResponse> response = clientRest.exchange(
                URL_8081, HttpMethod.GET, null, CamposResponse.class
        );
        return response.getBody();
    }
}
