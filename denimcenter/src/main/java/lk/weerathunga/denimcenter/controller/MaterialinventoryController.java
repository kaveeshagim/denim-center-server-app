package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.MaterialinventoryDao;
import lk.weerathunga.denimcenter.entity.Materialinventory;
import lk.weerathunga.denimcenter.entity.Materialinventory;
import lk.weerathunga.denimcenter.entity.Materialinventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/materialinventories")
public class MaterialinventoryController {

    @Autowired
    private MaterialinventoryDao materialinventorydao;

    @GetMapping(produces = "application/json")
    public List<Materialinventory> get(@RequestParam HashMap<String, String> params) {

        String number = params.get("number");
        String materialid = params.get("materialid");


        List<Materialinventory> materialinventories = this.materialinventorydao.findAll();

        if(params.isEmpty()) return materialinventories;

        Stream<Materialinventory> estream = materialinventories.stream();

        if(number!=null) estream = estream.filter(e -> e.getNumber().equals(number));
        if(materialid!=null) estream = estream.filter(e -> e.getMaterial().getId()==Integer.parseInt(materialid));


        return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<Materialinventory> get() {

        List<Materialinventory> materialinventories = this.materialinventorydao.findAll();

        materialinventories = materialinventories.stream().map(
                materialinventory -> {Materialinventory s = new Materialinventory();
                    s.setId(materialinventory.getId());
                    s.setNumber(materialinventory.getNumber());
                    s.setQty(materialinventory.getQty());
                    s.setReorderlevel(materialinventory.getReorderlevel());
                    s.setMaterial(materialinventory.getMaterial());
                    return s; }
        ).collect(Collectors.toList());

        return materialinventories;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Materialinventory materialinventory){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(materialinventorydao.findByNumber(materialinventory.getNumber())!=null)
            errors = errors+"<br> Existing Number";

        if(errors=="")
            materialinventorydao.save(materialinventory);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(materialinventory.getId()));
        response.put("url","/materialinventories/"+materialinventory.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Materialinventory materialinventory){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Materialinventory prod1 = materialinventorydao.findByNumber(materialinventory.getNumber());

        //System.out.println("ID-"+materialinventory.getId()+"-"+materialinventory.getNic());
        //if(prod1!=null) System.out.println("NIC-"+prod1.getNic());

        if(prod1!=null && materialinventory.getId()!=prod1.getId())
            errors = errors+"<br> Existing Number";

        //System.out.println(materialinventory.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") materialinventorydao.save(materialinventory);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(materialinventory.getId()));
        response.put("url","/materialinventories/"+materialinventory.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Materialinventory emp1 = materialinventorydao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> materialinventory Does Not Existed";
        if(errors=="") materialinventorydao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/materialinventorys/"+id);
        response.put("errors",errors);
        return response;
    }
}
