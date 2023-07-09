package com.wileymab.bookworm.api.controllers;

import com.wileymab.bookworm.api.controllers.utils.RestCallHandler;
import com.wileymab.bookworm.api.interfaces.TitleServiceInterface;
import com.wileymab.bookworm.api.model.Title;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/titles")
public class TitlesController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorsController.class);

    private final TitleServiceInterface titleService;


    public TitlesController(TitleServiceInterface titleService) {
        this.titleService = titleService;
    }


    // ========================================================================
    //  PUBLIC API
    //
    @GetMapping("/{id}")
    public ResponseEntity<?> getTitleById(@PathVariable String id) {
        return new RestCallHandler<>().execute(() -> titleService.getTitleById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTitles_EmptyPath() {
        return getAllTitles();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTitles_RootPath() {
        return getAllTitles();
    }


    @GetMapping("/find")
    public ResponseEntity<?> findAllTitlesByText(@RequestParam("q") String query) {
        if (StringUtils.isEmpty(query)) {
            LOG.error("Query param value for key \"q\" was empty.");
            return ResponseEntity.badRequest().build();
        }
        return new RestCallHandler<>().execute(() -> titleService.findAllWhereTitleContains(query));
    }

    @PostMapping("")
    public ResponseEntity<?> insertNewAuthor(@RequestBody Title title) {
        return new RestCallHandler<>().execute(() -> titleService.insertTitle(title));
    }

    @PutMapping("")
    public ResponseEntity<?> updateExistingAuthor(@RequestBody Title title) {
        return new RestCallHandler<>().execute(() -> {
            if ( title.getId() == null ) {
                return ResponseEntity.badRequest().build();
            }
            return titleService.updateTitle(title);
        });
    }

    // ========================================================================
    //  PRIVATE API
    //

    private ResponseEntity<?> getAllTitles() {
        return new RestCallHandler<>().execute(titleService::getAllTitles);
    }


}
