package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.ProductmovementDao;
import lk.weerathunga.denimcenter.entity.Productmovement;
import lk.weerathunga.denimcenter.entity.Productmovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/productmovements")
public class ProductmovementController {

    @Autowired
    private ProductmovementDao productmovementdao;

    @GetMapping(produces = "application/json")
    public List<Productmovement> get(@RequestParam HashMap<String, String> params) {
        
        String productinventoryid = params.get("productinventoryid");
        String movementtypeid = params.get("movementtypeid");


        List<Productmovement> productmovements = this.productmovementdao.findAll();

        if(params.isEmpty()) return productmovements;

        Stream<Productmovement> estream = productmovements.stream();

 
        if(productinventoryid!=null) estream = estream.filter(e -> e.getProductinventory().getId()==Integer.parseInt(productinventoryid));
        if(movementtypeid!=null) estream = estream.filter(e -> e.getMovementtype().getId()==Integer.parseInt(movementtypeid));


        return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Productmovement productmovement){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="")
            productmovementdao.save(productmovement);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(productmovement.getId()));
        response.put("url","/productmovements/"+productmovement.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Productmovement productmovement){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="") productmovementdao.save(productmovement);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(productmovement.getId()));
        response.put("url","/productmovements/"+productmovement.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Productmovement mov = productmovementdao.findAllById(id);
        if(mov==null)
            errors = errors+"<br> productmovement Does Not Existed";
        if(errors=="") productmovementdao.delete(mov);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/productmovements/"+id);
        response.put("errors",errors);
        return response;
    }
}
