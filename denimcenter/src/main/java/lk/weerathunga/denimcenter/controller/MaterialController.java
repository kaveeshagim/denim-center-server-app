package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.MaterialDao;
import lk.weerathunga.denimcenter.entity.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/materials")
public class MaterialController {

    @Autowired
    private MaterialDao materialdao;

    @GetMapping(produces = "application/json")
    public List<Material> get(@RequestParam HashMap<String, String> params) {

        String id = params.get("id");
        String name = params.get("name");
        String supplierid = params.get("supplierid");


        List<Material> materials = this.materialdao.findAll();

        if(params.isEmpty()) return materials;

        Stream<Material> estream = materials.stream();

                    if(id!=null) estream = estream.filter(e -> e.getId() ==  Integer.parseInt(id));
                    if(name!=null) estream = estream.filter(e -> e.getName().contains(name));
                    if(supplierid!=null) estream = estream.filter(e -> e.getSupplier().getId()==Integer.parseInt(supplierid));

                    return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<Material> get() {

        List<Material> materials = this.materialdao.findAll();

        materials = materials.stream().map(
                material -> { Material s = new Material();
                    s.setId(material.getId());
                    s.setName(material.getName());
                    s.setCostperunit(material.getCostperunit());
                    s.setSupplier(material.getSupplier());

                    return s; }
        ).collect(Collectors.toList());

        return materials;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Material material){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(materialdao.findByName(material.getName())!=null)
            errors = errors+"<br> Existing Name";

        if(errors=="")
            materialdao.save(material);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(material.getId()));
        response.put("url","/materials/"+material.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Material material){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Material mat = materialdao.findByName(material.getName());

        if(mat!=null && material.getId()!=mat.getId())
            errors = errors+"<br> Existing Name";

        if(errors=="") materialdao.save(material);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(material.getId()));
        response.put("url","/materials/"+material.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Material mat = materialdao.findAllById(id);
        if(mat==null)
            errors = errors+"<br> material Does Not Existed";
        if(errors=="") materialdao.delete(mat);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/materials/"+id);
        response.put("errors",errors);
        return response;
    }
}
