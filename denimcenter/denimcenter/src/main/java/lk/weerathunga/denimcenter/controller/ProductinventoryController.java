package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.ProductinventoryDao;
import lk.weerathunga.denimcenter.entity.Productinventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/productinventories")
public class ProductinventoryController {

    @Autowired
    private ProductinventoryDao productinventorydao;

    @GetMapping(produces = "application/json")
    public List<Productinventory> get(@RequestParam HashMap<String, String> params) {

        String number = params.get("number");
        String productid = params.get("productid");


        List<Productinventory> productinventories = this.productinventorydao.findAll();

        if(params.isEmpty()) return productinventories;

        Stream<Productinventory> estream = productinventories.stream();

        if(number!=null) estream = estream.filter(e -> e.getNumber().equals(number));
        if(productid!=null) estream = estream.filter(e -> e.getProduct().getId()==Integer.parseInt(productid));


        return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<Productinventory> get() {

        List<Productinventory> productinventories = this.productinventorydao.findAll();

        productinventories = productinventories.stream().map(
                productinventory -> {Productinventory s = new Productinventory();
                    s.setId(productinventory.getId());
                    s.setNumber(productinventory.getNumber());
                    s.setQty(productinventory.getQty());
                    s.setReorderlevel(productinventory.getReorderlevel());
                    s.setProduct(productinventory.getProduct());
                    return s; }
        ).collect(Collectors.toList());

        return productinventories;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Productinventory productinventory){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(productinventorydao.findByNumber(productinventory.getNumber())!=null)
            errors = errors+"<br> Existing Number";

        if(errors=="")
            productinventorydao.save(productinventory);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(productinventory.getId()));
        response.put("url","/productinventories/"+productinventory.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Productinventory productinventory){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Productinventory prod1 = productinventorydao.findByNumber(productinventory.getNumber());

        //System.out.println("ID-"+product.getId()+"-"+product.getNic());
        //if(prod1!=null) System.out.println("NIC-"+prod1.getNic());

        if(prod1!=null && productinventory.getId()!=prod1.getId())
            errors = errors+"<br> Existing Number";

        //System.out.println(product.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") productinventorydao.save(productinventory);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(productinventory.getId()));
        response.put("url","/productinventories/"+productinventory.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Productinventory emp1 = productinventorydao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> productinventory Does Not Existed";
        if(errors=="") productinventorydao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/productinventorys/"+id);
        response.put("errors",errors);
        return response;
    }
}
