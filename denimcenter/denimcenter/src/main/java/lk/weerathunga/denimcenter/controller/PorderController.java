package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.PorderDao;
import lk.weerathunga.denimcenter.entity.Porder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/porders")
public class PorderController {

    @Autowired
    private PorderDao porderdao;

    @GetMapping(produces = "application/json")
    public List<Porder> get(@RequestParam HashMap<String, String> params) {

        String number = params.get("number");
        String supplierid = params.get("supplierid");
        String orderstatusid = params.get("orderstatusid");
        String materialid = params.get("materialid");


        List<Porder> porders = this.porderdao.findAll();

        if(params.isEmpty()) return porders;

        Stream<Porder> estream = porders.stream();

                    if(supplierid!=null) estream = estream.filter(e -> e.getSupplier().getId()==Integer.parseInt(supplierid));
                    if(number!=null) estream = estream.filter(e -> e.getNumber().contains(number));
                    if(orderstatusid!=null) estream = estream.filter(e -> e.getOrderstatus().getId()==Integer.parseInt(orderstatusid));
                    if(materialid!=null) estream = estream.filter(e -> e.getMaterial().getId()==Integer.parseInt(materialid));

                    return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<Porder> get() {

        List<Porder> porders = this.porderdao.findAll();

        porders = porders.stream().map(
                porder -> {Porder s = new Porder();
                    s.setId(porder.getId());
                    s.setNumber(porder.getNumber());
                    s.setTotalprice(porder.getTotalprice());
                    s.setSupplier(porder.getSupplier());
                    return s; }
        ).collect(Collectors.toList());

        return porders;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Porder porder){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(porderdao.findByNumber(porder.getNumber())!=null)
            errors = errors+"<br> Existing Number";

        if(errors=="")
            porderdao.save(porder);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(porder.getId()));
        response.put("url","/porders/"+porder.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Porder porder){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Porder emp1 = porderdao.findByNumber(porder.getNumber());

        //System.out.println("ID-"+porder.getId()+"-"+porder.getNumber());
        //if(emp1!=null) System.out.println("NUMBER-"+emp1.getNumber());

        if(emp1!=null && porder.getId()!=emp1.getId())
            errors = errors+"<br> Existing NUMBER";

        //System.out.println(porder.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") porderdao.save(porder);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(porder.getId()));
        response.put("url","/porders/"+porder.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Porder emp1 = porderdao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> porder Does Not Existed";
        if(errors=="") porderdao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/porders/"+id);
        response.put("errors",errors);
        return response;
    }
}
