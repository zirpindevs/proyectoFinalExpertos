package com.example.proyectoFinalExpertos.controller;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.service.impl.TagServiceImpl;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TagController {

    private final Logger log = LoggerFactory.getLogger(TagController.class);

    private final TagServiceImpl tagService;

    public TagController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }


   /**
     * CREATE A TAG
     * @param tagName
     * @return ResponseEntity<Tag>
     * @throws URISyntaxException
     */
    @PostMapping("/etiquetas")
    public ResponseEntity<Tag> createTag(@RequestBody String tagName) throws URISyntaxException {
        log.debug("REST request to create a tag: {} ", tagName);

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&createTag controller&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(tagName);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");


        if (tagName.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Tag createTag = this.tagService.createTag(tagName);

        return ResponseEntity
                .created(new URI("/api/tags/" + createTag.getName()))
                .body(createTag);
    }

     /**
     * UPDATE TAG
     * @param id
     * @param modifiedTag
     * @return ResponseEntity<Tag>
     */
    @PutMapping("/etiquetas/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag modifiedTag){
        log.debug("REST request to update one tag: {} ",modifiedTag);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Tag updateTag = this.tagService.updateTag(id, modifiedTag);

        if(updateTag.getId() == null) {
            log.warn("update tag without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(updateTag);
    }


        /**
     * FIND ALL TAGS
     * @return List<Tag>
     */
/*    @GetMapping("/etiquetas")
    public List<Tag> findTags(){
        log.debug("REST request to find all Tags");

        return this.tagService.findAll();
    }*/

    /**
     * FIND ALL EXPERTS BY FILTER
     * @return List<Expert>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/etiquetas")
    public List<Tag> controllerMethod(@RequestParam Map<String, String> customQuery){
        log.debug("REST request to find all tags");

        String nombre = "";
        String limite = "5";
        String pagina = "0";

        if(customQuery.containsKey("nombre"))
            nombre = customQuery.get("nombre");
        if(customQuery.containsKey("limite"))
            limite = customQuery.get("limite");
        if(customQuery.containsKey("pagina"))
            pagina = customQuery.get("pagina");

/*
        System.out.println("****************************FIND ALL EXPERTS BY FILTER*************************************************");
        System.out.println(nombre);
        System.out.println(limite);
        System.out.println(pagina);
        System.out.println("************************************************************************************");
*/


        return this.tagService.findAllByFilter(nombre, limite, pagina);
        }

    /**
     * FIND ONE TAG BY ID
     * @param id
     * @return ResponseEntity<Tag>
     * @throws URISyntaxException
     */
    @GetMapping("/etiquetas/{id}")
    public ResponseEntity<Tag> findTagId(@PathVariable Long id) throws URISyntaxException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);

        criteria.where(builder.equal(root.get("id"), id));

        Tag findTag = this.tagService.findOne(id);

        session.close();

        return ResponseEntity.ok().body(findTag);
    }

    @DeleteMapping("/etiquetas/{id}")
    public ResponseEntity<Void> deleteExpert(@PathVariable Long id){
        log.debug("REST request to delete a expert: {} ", id);

        Tag tagToDelete = this.tagService.findOne(id);


        if (tagToDelete.getId() == null) {
            log.warn("expert not exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.tagService.deleteTag(tagToDelete);
        return ResponseEntity.noContent().build();
    }
}
