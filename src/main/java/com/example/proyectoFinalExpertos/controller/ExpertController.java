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
     * @param expert
     * @return ResponseEntity<Expert>
     * @throws URISyntaxException
     */
    @PostMapping("/expertos")
    public ResponseEntity<Expert> createExpert(@RequestBody Expert expert) throws URISyntaxException {
        log.debug("REST request to create an expert: {} ", expert);

        if (expert.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Expert createExpert = this.expertService.createExpert(expert);

        return ResponseEntity
                .created(new URI("/api/experts/" + createExpert.getName()))
                .body(createExpert);
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

        Expert updateExpert = this.expertService.updateExpert(id, modifiedExpert);

        if (updateExpert.getId() == null) {
            log.warn("update expert without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(updateExpert);
    }

    /**
     * FIND ALL EXPERTS
     * @return List<Expert>
     */

//    @GetMapping("/expertos")
//    public List<Expert> findExpert(){
//        log.debug("REST request to find all experts");
//
//        return this.expertService.findAll();
//    }

    //http://localhost:8080/expertos?name=nombre1&surname=surname1&nif=11
    @RequestMapping(method = RequestMethod.GET, value = "/expertos")
    public List<Expert> controllerMethod(@RequestParam Map<String, String> customQuery){
        log.debug("REST request to find all experts");

        System.out.println("***************************************************************************");
        System.out.println("customQuery = nombre " + customQuery.containsKey("nombre"));
        System.out.println("customQuery = etiqueta " + customQuery.containsKey("etiqueta"));
        System.out.println("customQuery = modalidad " + customQuery.containsKey("modalidad"));
        System.out.println("customQuery = estado " + customQuery.containsKey("estado"));
        System.out.println("customQuery = limite " + customQuery.containsKey("limite"));
        System.out.println("customQuery = pagina " + customQuery.containsKey("pagina"));
        System.out.println("***************************************************************************");



        return this.expertService.findAll();
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
