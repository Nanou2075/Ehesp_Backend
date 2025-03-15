package com.elearning.elearning.memory;


import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
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
public class MemoryServiceImpl implements MemoryService {
    private final MemoryRepository memoryRepository;






     /*
    Enregistrement  de la section
     */

    @Override
    public Response save(Memory memory) {
        memoryRepository.save(memory);
        return new Response(OK, MEMORY_SAVE);
    }


    /*
  Modification  de la section
   */
    @Override
    public Response update(String id, Memory value) {
        Memory memory = memoryRepository.findById(id);
        memoryRepository.save(memory);
        return new Response(OK, MEMORY_UPDATE);
    }



      /*
    Suppression  de la matière
     */

    @Override
    public Response delete(String id) {
        Memory memory = memoryRepository.findById(id);
        if (memory == null) {
            throw new NotFoundException(NO, MEMORY_NO_EXIT);
        }
        memoryRepository.delete(memory);
        return new Response(OK, MEMORY_DELETE);
    }


    /*
  recuperation par id  de la matière
   */
    @Override
    public Response get(String id) {
        Memory memory = memoryRepository.findById(id);
        if (memory == null) {
            throw new NotFoundException(NO, MEMORY_NO_EXIT);
        }
        return new Response(OK, memory);
    }


    /*
  recuperation de la liste   des sections
   */
    @Override
    public Response getAll() {
        List<Memory> memories = memoryRepository.findAll(Sort.by("createdDate").ascending());
        if (memories.isEmpty()) {
            throw new NotFoundException(NO, MEMORY_EMPTY);
        }
        memories.forEach(memory -> {
            if (memory.getDate().isBefore(LocalDate.now()) && memory.getTime().isBefore(LocalTime.now())) {
                memory.setDone(true);
                memoryRepository.save(memory);
            }
        });
        return new Response(OK, memories);
    }
}
