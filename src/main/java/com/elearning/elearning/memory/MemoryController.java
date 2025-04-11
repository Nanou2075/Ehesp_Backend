package com.elearning.elearning.memory;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.speciality.Speciality;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemoryController implements MemoryResource {

    private final MemoryService memoryService;

    @Override
    public Response save(Memory memory) {
        return memoryService.save(memory);
    }

    @Override
    public Response update(String id, Memory memory) {
        return memoryService.update(id, memory);
    }

    @Override
    public Response get(String id) {
        return memoryService.get(id);
    }

    @Override
    public Response getAll() {
        return memoryService.getAll();
    }

    @Override
    public Response getStudentMemory() {
        return memoryService.getStudentMemory();
    }

    @Override
    public Response getStudentMemoryBySpeciality(Speciality speciality) {
        return memoryService.getAllBySpeciality(speciality);
    }

    @Override
    public Response delete(String id) {
        return memoryService.delete(id);
    }

}


