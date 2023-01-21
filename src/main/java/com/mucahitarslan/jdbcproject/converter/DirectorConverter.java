package com.mucahitarslan.jdbcproject.converter;

import com.mucahitarslan.jdbcproject.dto.request.DirectorRequest;
import com.mucahitarslan.jdbcproject.dto.response.DirectorResponse;
import com.mucahitarslan.jdbcproject.model.Director;
import org.springframework.stereotype.Component;

@Component
public class DirectorConverter {
    public DirectorResponse toDirectorResponse(Director director){
        DirectorResponse directorResponse = new DirectorResponse();
        directorResponse.setName(director.getName());
        directorResponse.setBirthDate(director.getBirthDate());
        return directorResponse;
    }

    public Director toDirector(DirectorRequest directorRequest){
        Director director = new Director();
        director.setName(directorRequest.getName());
        director.setBirthDate(directorRequest.getBirthDate());
        return director;
    }
}
