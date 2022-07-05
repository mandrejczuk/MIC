package pz1.MIC.controller;

import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import pz1.MIC.model.Class;
import pz1.MIC.repository.UserRepository;
import pz1.MIC.service.ClassService;
import pz1.MIC.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    private final ClassService classService;

    @Autowired
    UserRepository userRepository;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        classService.delete(id);
    }

    @PostMapping("/add")
    public Class addClass(@RequestBody Class classe)
    {
        return classService.add(classe);
    }

    @GetMapping("{id}")
    public Class getClass(@PathVariable Long id){
        return classService.show(id);
    }

    @GetMapping("/all")
    public List<Class> getAll(@RequestHeader (name="Authorization") String token, @RequestParam int size, @RequestParam int page){

        return classService.showAll(page, size).getContent();
    }
}
