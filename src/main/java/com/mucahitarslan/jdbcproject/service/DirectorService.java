package com.mucahitarslan.jdbcproject.service;

import com.mucahitarslan.jdbcproject.converter.DirectorConverter;
import com.mucahitarslan.jdbcproject.dto.request.DirectorRequest;
import com.mucahitarslan.jdbcproject.dto.response.DirectorResponse;
import com.mucahitarslan.jdbcproject.repository.IDirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DirectorService {
    private final DirectorConverter directorConverter;
    private final IDirectorRepository directorRepository;
    public DirectorService(IDirectorRepository directorRepository, DirectorConverter directorConverter) {
        this.directorConverter = directorConverter;
        this.directorRepository = directorRepository;
    }

    public List<DirectorResponse> findAllDirectors(){
        return StreamSupport.stream(directorRepository.findAll().spliterator(),false)
                .map(directorConverter::toDirectorResponse)
                .collect(Collectors.toList());
    }

    public DirectorResponse save(DirectorRequest directorRequest){
    return directorConverter.toDirectorResponse(directorRepository.save(directorConverter.toDirector(directorRequest)));

    }
}
