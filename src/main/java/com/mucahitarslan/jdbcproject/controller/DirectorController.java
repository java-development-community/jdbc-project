package com.mucahitarslan.jdbcproject.controller;

import com.mucahitarslan.jdbcproject.dto.request.DirectorRequest;
import com.mucahitarslan.jdbcproject.dto.response.DirectorResponse;
import com.mucahitarslan.jdbcproject.model.Director;
import com.mucahitarslan.jdbcproject.service.DirectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/directors")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<DirectorResponse> findAllDirectors(){
        return directorService.findAllDirectors();
    }
    @PostMapping
    public DirectorResponse saveDirector(@RequestBody DirectorRequest directorRequest){
        return directorService.save(directorRequest);
    }
}
