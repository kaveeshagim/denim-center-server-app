package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.GrnDao;
import lk.weerathunga.denimcenter.entity.Grn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/grns")
public class GrnController {

    @Autowired
    private GrnDao grndao;

    @GetMapping(produces = "application/json")
    public List<Grn> get(@RequestParam HashMap<String, String> params) {

        String number = params.get("number");
        String porderid = params.get("porderid");
        String supplierid = params.get("supplierid");
        String grnstatusid = params.get("grnstatusid");


        List<Grn> grns = this.grndao.findAll();

        if(params.isEmpty()) return grns;

        Stream<Grn> estream = grns.stream();

        if(number!=null) estream = estream.filter(e -> e.getNumber().equals(number));
        if(porderid!=null) estream = estream.filter(e -> e.getPorder().getId()==Integer.parseInt(porderid));
        if(supplierid!=null) estream = estream.filter(e -> e.getSupplier().getId()==Integer.parseInt(supplierid));
        if(grnstatusid!=null) estream = estream.filter(e -> e.getGrnstatus().getId()==Integer.parseInt(grnstatusid));


        return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<Grn> get() {

        List<Grn> grns = this.grndao.findAll();

        grns = grns.stream().map(
                grn -> {Grn s = new Grn();
                    s.setId(grn.getId());
                    s.setNumber(grn.getNumber());
                    s.setPaidamount(grn.getPaidamount());
                    return s; }
        ).collect(Collectors.toList());

        return grns;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Grn grn){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(grndao.findByNumber(grn.getNumber())!=null)
            errors = errors+"<br> Existing Number";

        if(errors=="")
            grndao.save(grn);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(grn.getId()));
        response.put("url","/grns/"+grn.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Grn grn){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Grn prod1 = grndao.findByNumber(grn.getNumber());

        if(prod1!=null && grn.getId()!=prod1.getId())
            errors = errors+"<br> Existing Number";

        if(errors=="") grndao.save(grn);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(grn.getId()));
        response.put("url","/grns/"+grn.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Grn emp1 = grndao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> grn Does Not Existed";
        if(errors=="") grndao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/grns/"+id);
        response.put("errors",errors);
        return response;
    }
}
