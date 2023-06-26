package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.CorderDao;
import lk.weerathunga.denimcenter.entity.Corder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/corders")
public class CorderController {

    @Autowired
    private CorderDao corderdao;

    @GetMapping(produces = "application/json")
    public List<Corder> get(@RequestParam HashMap<String, String> params) {

        String number = params.get("number");
        String customerid = params.get("customerid");
        String orderstatusid = params.get("orderstatusid");
        String productid = params.get("productid");


        List<Corder> corders = this.corderdao.findAll();

        if(params.isEmpty()) return corders;

        Stream<Corder> estream = corders.stream();

        if(number!=null) estream = estream.filter(e -> e.getNumber().contains(number));
        if(customerid!=null) estream = estream.filter(e -> e.getCustomer().getId()==Integer.parseInt(customerid));
        if(orderstatusid!=null) estream = estream.filter(e -> e.getOrderstatus().getId()==Integer.parseInt(orderstatusid));
        if(productid!=null) estream = estream.filter(e -> e.getProduct().getId()==Integer.parseInt(productid));

        return estream.collect(Collectors.toList());
    }
    @GetMapping(path = "/list", produces = "application/json")
    public List<Corder> get() {

        List<Corder> corders = this.corderdao.findAll();

        corders = corders.stream().map(
                corder -> {Corder s = new Corder();
                    s.setId(corder.getId());
                    s.setNumber(corder.getNumber());
                    s.setTotalprice(corder.getTotalprice());
                    s.setCustomer(corder.getCustomer());
                    return s; }
        ).collect(Collectors.toList());

        return corders;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Corder corder){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(corderdao.findByNumber(corder.getNumber())!=null)
            errors = errors+"<br> Existing Number";

        if(errors=="")
            corderdao.save(corder);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(corder.getId()));
        response.put("url","/corders/"+corder.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Corder corder){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Corder emp1 = corderdao.findByNumber(corder.getNumber());

        //System.out.println("ID-"+corder.getId()+"-"+corder.getNic());
        //if(emp1!=null) System.out.println("NIC-"+emp1.getNic());

        if(emp1!=null && corder.getId()!=emp1.getId())
            errors = errors+"<br> Existing Number";

        //System.out.println(corder.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") corderdao.save(corder);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(corder.getId()));
        response.put("url","/corders/"+corder.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Corder emp1 = corderdao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> corder Does Not Existed";
        if(errors=="") corderdao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/corders/"+id);
        response.put("errors",errors);
        return response;
    }
}

