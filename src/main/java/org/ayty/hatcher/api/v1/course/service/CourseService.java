package org.ayty.hatcher.api.v1.course.service;


import org.ayty.hatcher.api.v1.course.dto.Course;
import org.ayty.hatcher.api.v1.course.exception.ObjectNotFoundException;
import org.ayty.hatcher.api.v1.course.jpa.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public Course findById (Long id) {
        Optional<Course> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Course.class.getName()));
    }
    public List<Course> findAll() {
        return repository.findAll();
    }
    public Course update (Long id, Course obj) {
        Course newObj = findById(id);
        newObj.setName(obj.getName());
        newObj.setDescription(obj.getDescription());
        return repository.save(newObj);
    }
    public Course create(Course obj) {
        obj.setId(null);
        return repository.save(obj);
    }
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}