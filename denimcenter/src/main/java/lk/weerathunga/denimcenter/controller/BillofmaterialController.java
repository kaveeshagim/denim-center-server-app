package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.BillofmaterialDao;
import lk.weerathunga.denimcenter.entity.Billofmaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/billofmaterials")
public class BillofmaterialController {

    @Autowired
    private BillofmaterialDao billofmaterialdao;

    @GetMapping(produces = "application/json")
    public List<Billofmaterial> get(@RequestParam HashMap<String, String> params) {
        
        String productionorderid = params.get("productionorderid");


        List<Billofmaterial> billofmaterials = this.billofmaterialdao.findAll();

        if(params.isEmpty()) return billofmaterials;

        Stream<Billofmaterial> estream = billofmaterials.stream();

        if(productionorderid!=null) estream = estream.filter(e -> e.getProductionorder().getId()==Integer.parseInt(productionorderid));


        return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Billofmaterial billofmaterial){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="")
            billofmaterialdao.save(billofmaterial);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(billofmaterial.getId()));
        response.put("url","/billofmaterials/"+billofmaterial.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Billofmaterial billofmaterial){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="") billofmaterialdao.save(billofmaterial);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(billofmaterial.getId()));
        response.put("url","/billofmaterials/"+billofmaterial.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Billofmaterial bom = billofmaterialdao.findAllById(id);
        if(bom==null)
            errors = errors+"<br> billofmaterial Does Not Existed";
        if(errors=="") billofmaterialdao.delete(bom);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/billofmaterials/"+id);
        response.put("errors",errors);
        return response;
    }
}
