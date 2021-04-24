package com.example.proyectoFinalExpertos.controller;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExpertController {

    private final Logger log = LoggerFactory.getLogger(com.example.proyectoFinalExpertos.controller.ExpertController.class);

    private final ExpertService expertService;

    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
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

        if (expertToCreate.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Expert createdExpert = this.expertService.createExpert(expertToCreate);

        return ResponseEntity
                .created(new URI("/api/expertos/" + createdExpert.getNombre()))
                .body(createdExpert);
    }

    /**
     * UPDATE EXPERT
     *
     * @param id
     * @param modifiedExpert
     * @return ResponseEntity<Expert>
     */
    @PutMapping("/expertos/{id}")
    public ResponseEntity<Expert> updateExpert(@PathVariable Long id, @RequestBody Expert modifiedExpert) {
        log.debug("REST request to update one expert: {} ", modifiedExpert);

        Expert findUpdateExpert = this.expertService.findOne(id);

        if (findUpdateExpert.getId() == null) {
            log.warn("update expert without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Expert updateExpert = this.expertService.updateExpert(id, modifiedExpert);


     return ResponseEntity.ok().body(updateExpert);

    }

    /**
     * FIND ALL EXPERTS
     * @return List<Expert>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/expertos")
    public List<Expert> controllerMethod(@RequestParam Map<String, String> customQuery){
        log.debug("REST request to find all experts");

        String nombre = "";
        String etiqueta = "";
        String modalidad = "";
        String estado = "";
        String limite = "15";
        String pagina = "0";

        if(customQuery.containsKey("nombre"))
            nombre = customQuery.get("nombre");
        if(customQuery.containsKey("etiqueta"))
            etiqueta = customQuery.get("etiqueta");
        if(customQuery.containsKey("modalidad"))
            modalidad = customQuery.get("modalidad");
        if(customQuery.containsKey("estado"))
            estado = customQuery.get("estado");
        if(customQuery.containsKey("limite"))
            limite = customQuery.get("limite");
        if(customQuery.containsKey("pagina"))
            pagina = customQuery.get("pagina");

        System.out.println("************************************************************************************");
        System.out.println(nombre);
        System.out.println(etiqueta);
        System.out.println(modalidad);
        System.out.println(estado);
        System.out.println(limite);
        System.out.println(pagina);
        System.out.println("************************************************************************************");


        return this.expertService.findAllByFilter(nombre, etiqueta, modalidad, estado, limite, pagina);

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
