package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.SupplierreturnDao;
import lk.weerathunga.denimcenter.entity.Supplierreturn;
import lk.weerathunga.denimcenter.entity.Supplierreturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/supplierreturns")
public class SupplierreturnController {

    @Autowired
    private SupplierreturnDao supplierreturndao;

    @GetMapping(produces = "application/json")
    public List<Supplierreturn> get(@RequestParam HashMap<String, String> params) {
        
        String supplierid = params.get("supplierid");
        String grnid = params.get("grnid");


        List<Supplierreturn> supplierreturns = this.supplierreturndao.findAll();

        if(params.isEmpty()) return supplierreturns;

        Stream<Supplierreturn> estream = supplierreturns.stream();

        if(supplierid!=null) estream = estream.filter(e -> e.getSupplier().getId()==Integer.parseInt(supplierid));
        if(grnid!=null) estream = estream.filter(e -> e.getGrn().getId()==Integer.parseInt(grnid));


        return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Supplierreturn supplierreturn){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="")
            supplierreturndao.save(supplierreturn);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(supplierreturn.getId()));
        response.put("url","/supplierreturns/"+supplierreturn.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Supplierreturn supplierreturn){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="") supplierreturndao.save(supplierreturn);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(supplierreturn.getId()));
        response.put("url","/supplierreturns/"+supplierreturn.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Supplierreturn ret = supplierreturndao.findAllById(id);
        if(ret==null)
            errors = errors+"<br> supplierreturn Does Not Existed";
        if(errors=="") supplierreturndao.delete(ret);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/supplierreturns/"+id);
        response.put("errors",errors);
        return response;
    }
}
