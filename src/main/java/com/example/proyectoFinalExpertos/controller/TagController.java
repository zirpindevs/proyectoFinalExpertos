package com.example.proyectoFinalExpertos.controller;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.repository.TagRepository;
import com.example.proyectoFinalExpertos.service.TagService;
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

@RestController
@CrossOrigin(origins = "https://proyecto-final-expertos-front-5468r47tm-zirpindevs.vercel.app/etiquetas", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

@RequestMapping("/api")
public class TagController {

    private final Logger log = LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;
    private final TagRepository tagRepository;

        public TagController(TagService tagService, TagRepository tagRepository) {
        this.tagService = tagService;
            this.tagRepository = tagRepository;
        }


    /**
     * CREATE A TAG
     * @param tagToCreate
     * @return ResponseEntity<Tag>
     * @throws URISyntaxException
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/etiquetas")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tagToCreate) throws URISyntaxException {
        log.debug("REST request to create new a tag: {} ", tagToCreate);


        if (tagToCreate.getName() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Tag checkTag = this.tagService.findByName(tagToCreate.getName());

        if(checkTag == null) {
            Tag createTag = this.tagService.createTag(tagToCreate);

            return ResponseEntity
                    .created(new URI("/api/etiquetas/" + createTag.getName()))
                    .body(createTag);
        }
        else
        {
          log.warn("already in use");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

     /**
     * UPDATE TAG
     * @param modifiedTag
     * @return ResponseEntity<Tag>
     */
    @PutMapping("/etiquetas")
    public ResponseEntity<Tag> updateTag(@RequestBody Tag modifiedTag){
        log.debug("REST request to update one tag: {} ",modifiedTag);

        if (modifiedTag.getId() == null) {
            log.warn("update tag without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Tag updateTag = this.tagService.updateTag(modifiedTag);

        if(updateTag == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(updateTag);

    }


    /**
     * FIND ALL TAGS
     * @return List<Tag>
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/etiquetas")
    public List<Tag> findTags(){
        log.debug("REST request to find all Tags");

        return this.tagRepository.findAll();
    }

    /**
     * FIND ONE TAG BY ID
     * @param id
     * @return ResponseEntity<Tag>
     * @throws URISyntaxException
     */
    @GetMapping("/etiquetas/{id}")
    public ResponseEntity<Tag> findTagId(@PathVariable Long id) throws URISyntaxException {

        Tag findTag = this.tagService.findOne(id);

        if (findTag == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(findTag);

    }

    @DeleteMapping("/etiquetas/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id){
        log.debug("REST request to delete a tag: {} ", id);

        Tag tagToDelete = this.tagService.findOne(id);

        if (tagToDelete.getId() == null) {
            log.warn("tag not exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.tagService.deleteTag(tagToDelete);
        return ResponseEntity.noContent().build();
    }
}
