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

        /*if(productmovementdao.findByCode(productmovement.getCode())!=null)
            errors = errors+"<br> Existing Code";*/

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

        //Productmovement prod1 = productmovementdao.findByCode(productmovement.getCode());

        //System.out.println("ID-"+productmovement.getId()+"-"+productmovement.getNic());
        //if(prod1!=null) System.out.println("NIC-"+prod1.getNic());

        /*if(prod1!=null && productmovement.getId()!=prod1.getId())
            errors = errors+"<br> Existing Code";*/

        //System.out.println(productmovement.getFirstname());
        //System.out.println("Err-"+errors);

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
        Productmovement emp1 = productmovementdao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> productmovement Does Not Existed";
        if(errors=="") productmovementdao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/productmovements/"+id);
        response.put("errors",errors);
        return response;
    }
}
