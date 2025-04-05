package com.elearning.elearning.appreciation;


import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.security.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.memory.MemoryMessage.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AppreciationServiceImpl implements AppreciationService {
    private final AppreciationRepository appreciationRepository;
    private final AuthenticationService authenticationService;






     /*
    Enregistrement  de la section
     */

    @Override
    public Response save(Appreciation appreciation) {
        appreciationRepository.save(appreciation);
        return new Response(OK, MEMORY_SAVE);
    }


    /*
  Modification  de la section
   */
    @Override
    public Response update(String id, Appreciation value) {
        Appreciation appreciation = appreciationRepository.findById(id);
        appreciationRepository.save(appreciation);
        return new Response(OK, MEMORY_UPDATE);
    }



      /*
    Suppression  de la matière
     */

    @Override
    public Response delete(String id) {
        Appreciation appreciation = appreciationRepository.findById(id);
        if (appreciation == null) {
            throw new NotFoundException(NO, MEMORY_NO_EXIT);
        }
        appreciationRepository.delete(appreciation);
        return new Response(OK, MEMORY_DELETE);
    }


    /*
  recuperation par id  de la matière
   */
    @Override
    public Response get(String id) {
        Appreciation appreciation = appreciationRepository.findById(id);
        if (appreciation == null) {
            throw new NotFoundException(NO, MEMORY_NO_EXIT);
        }
        return new Response(OK, appreciation);
    }


    /*
  recuperation de la liste   des sections
   */
    @Override
    public Response getAll() {
        List<Appreciation> memories = appreciationRepository.findAll(Sort.by("createdDate").ascending());
        if (memories.isEmpty()) {
            throw new NotFoundException(NO, MEMORY_EMPTY);
        }

        return new Response(OK, memories);
    }

    /*
recuperation de la liste   des sections
*/
    @Override
    public Response getStudentMemory() {
        List<Appreciation> memories = appreciationRepository.findByStudent(authenticationService.currentStudent());
        if (memories.isEmpty()) {
            throw new NotFoundException(NO, MEMORY_EMPTY);
        }
        return new Response(OK, memories);
    }
}
