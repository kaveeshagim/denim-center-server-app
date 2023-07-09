package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.ProductionorderDao;
import lk.weerathunga.denimcenter.entity.Productionorder;
import lk.weerathunga.denimcenter.entity.Productionorder;
import lk.weerathunga.denimcenter.entity.Productionorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/productionorders")
public class ProductionorderController {

    @Autowired
    private ProductionorderDao productionorderdao;

    @GetMapping(produces = "application/json")
    public List<Productionorder> get(@RequestParam HashMap<String, String> params) {

        String number = params.get("number");


        List<Productionorder> productionorders = this.productionorderdao.findAll();

        if(params.isEmpty()) return productionorders;

        Stream<Productionorder> estream = productionorders.stream();

        if(number!=null) estream = estream.filter(e -> e.getNumber().equals(number));


        return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<Productionorder> get() {

        List<Productionorder> productionorders = this.productionorderdao.findAll();

        productionorders = productionorders.stream().map(
                productionorder -> { Productionorder s = new Productionorder();
                    s.setId(productionorder.getId());
                    s.setNumber(productionorder.getNumber());
                    return s; }
        ).collect(Collectors.toList());

        return productionorders;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Productionorder productionorder){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(productionorderdao.findByNumber(productionorder.getNumber())!=null)
            errors = errors+"<br> Existing Number";

        if(errors=="")
            productionorderdao.save(productionorder);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(productionorder.getId()));
        response.put("url","/products/"+productionorder.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Productionorder productionorder){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Productionorder pod = productionorderdao.findByNumber(productionorder.getNumber());

        if(pod!=null && productionorder.getId()!=pod.getId())
            errors = errors+"<br> Existing Number";

        if(errors=="") productionorderdao.save(productionorder);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(productionorder.getId()));
        response.put("url","/products/"+productionorder.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Productionorder pod = productionorderdao.findAllById(id);
        if(pod==null)
            errors = errors+"<br> productionorder Does Not Existed";
        if(errors=="") productionorderdao.delete(pod);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/productionorders/"+id);
        response.put("errors",errors);
        return response;
    }
}
