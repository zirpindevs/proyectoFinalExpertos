package com.example.proyectoFinalExpertos.controller;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.repository.ExpertRepository;
import com.example.proyectoFinalExpertos.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ExpertController {

    private final Logger log = LoggerFactory.getLogger(com.example.proyectoFinalExpertos.controller.ExpertController.class);

    private final ExpertService expertService;


    private final ExpertRepository expertRepository;

    public ExpertController(ExpertService expertService, ExpertRepository expertRepository) {
        this.expertService = expertService;
        this.expertRepository = expertRepository;
    }


    /**
     * CREATE EXPERT
     *
     * @param expertToCreate
     * @return ResponseEntity<Expert>
     * @throws URISyntaxException
     */
    @PostMapping("/expertos")
    public ResponseEntity<Expert> createExpert(@RequestBody Expert expertToCreate) throws URISyntaxException {
        log.debug("REST request to create an expert: {} ", expertToCreate);

        if (expertToCreate.getNombre() == null || expertToCreate.getTelefono() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Expert createdExpert = this.expertService.createExpert(expertToCreate);

        return ResponseEntity
                .created(new URI("/api/expertos/" + createdExpert.getNombre()))
                .body(createdExpert);
    }

    /**
     * UPDATE EXPERT
     *
     * @param modifiedExpert
     * @return ResponseEntity<Expert>
     */
    @PutMapping("/expertos")
    public ResponseEntity<Expert> updateExpert(@RequestBody Expert modifiedExpert) {
        log.debug("REST request to update one expert: {} ", modifiedExpert);


        if (modifiedExpert.getId() == null) {
            log.warn("update expert without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Expert updateExpert = this.expertService.updateExpert(modifiedExpert);

        if(updateExpert == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(updateExpert);

    }

    /**
     * FIND ALL EXPERTS
     * @return List<Expert>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/expertos")
    public ResponseEntity<Map<String, Object>>getAll(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String estado,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "3") int tamano){

        log.debug("REST request to find all experts");


        try {
            List<Expert> experts = new ArrayList<Expert>();
            Pageable paging = PageRequest.of(pagina, tamano);

            Page<Expert> pageExpert;
            if (nombre != null) {
               pageExpert = expertRepository.findByNombre(nombre, paging);

               experts = pageExpert.getContent();


                Map<String, Object> response = new HashMap<>();
                response.put("experts", experts);
                response.put("currentPage", pageExpert.get());
                response.put("totalItems", pageExpert.getTotalElements());
                response.put("totalPages", pageExpert.getTotalPages());

                return new ResponseEntity<>(response, HttpStatus.OK);

            }

            if (estado != null) {
                pageExpert = expertRepository.findByEstado(estado, paging);

                System.out.println(pageExpert);
                experts = pageExpert.getContent();

                Map<String, Object> response = new HashMap<>();
                response.put("experts", experts);
                response.put("currentPage", pageExpert.get());
                response.put("totalItems", pageExpert.getTotalElements());
                response.put("totalPages", pageExpert.getTotalPages());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {
                pageExpert = expertRepository.findAll(paging);

                experts = pageExpert.getContent();

                Map<String, Object> response = new HashMap<>();
                response.put("experts", experts);
                response.put("currentPage", pageExpert.get());
                response.put("totalItems", pageExpert.getTotalElements());
                response.put("totalPages", pageExpert.getTotalPages());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // return this.expertService.findAllByFilter(nombre, estado, tamano, pagina);

        //return this.expertService.findAll();

    }
    /**
     * Find EXPERT BY ID
     *
     * @param id
     * @return ResponseEntity<Expert>
     * @throws URISyntaxException
     */
    @GetMapping("/expertos/{id}")
    public ResponseEntity<Expert> findExpertId(@PathVariable Long id) throws URISyntaxException {
        Expert findExpert = this.expertService.findOne(id);

        if (findExpert == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(findExpert);

    }

    @DeleteMapping("/expertos/{id}")
    public ResponseEntity<Void> deleteExpert(@PathVariable Long id){
        log.debug("REST request to delete a expert: {} ", id);

        Expert expertToDelete = this.expertService.findOne(id);

        if (expertToDelete.getId() == null) {
            log.warn("expert not exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.expertService.deleteExpert(expertToDelete);
        return ResponseEntity.noContent().build();
    }

}
