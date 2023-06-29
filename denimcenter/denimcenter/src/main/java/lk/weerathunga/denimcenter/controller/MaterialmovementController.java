package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.MaterialmovementDao;
import lk.weerathunga.denimcenter.entity.Materialmovement;
import lk.weerathunga.denimcenter.entity.Materialmovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/materialmovements")
public class MaterialmovementController {

    @Autowired
    private MaterialmovementDao materialmovementdao;

    @GetMapping(produces = "application/json")
    public List<Materialmovement> get(@RequestParam HashMap<String, String> params) {
        
        String materialinventoryid = params.get("materialinventoryid");
        String movementtypeid = params.get("movementtypeid");


        List<Materialmovement> materialmovements = this.materialmovementdao.findAll();

        if(params.isEmpty()) return materialmovements;

        Stream<Materialmovement> estream = materialmovements.stream();

 
        if(materialinventoryid!=null) estream = estream.filter(e -> e.getMaterialinventory().getId()==Integer.parseInt(materialinventoryid));
        if(movementtypeid!=null) estream = estream.filter(e -> e.getMovementtype().getId()==Integer.parseInt(movementtypeid));


        return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Materialmovement materialmovement){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        /*if(materialmovementdao.findByCode(materialmovement.getCode())!=null)
            errors = errors+"<br> Existing Code";*/

        if(errors=="")
            materialmovementdao.save(materialmovement);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(materialmovement.getId()));
        response.put("url","/materialmovements/"+materialmovement.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Materialmovement materialmovement){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        //Materialmovement prod1 = materialmovementdao.findByCode(materialmovement.getCode());

        //System.out.println("ID-"+materialmovement.getId()+"-"+materialmovement.getNic());
        //if(prod1!=null) System.out.println("NIC-"+prod1.getNic());

        /*if(prod1!=null && materialmovement.getId()!=prod1.getId())
            errors = errors+"<br> Existing Code";*/

        //System.out.println(materialmovement.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") materialmovementdao.save(materialmovement);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(materialmovement.getId()));
        response.put("url","/materialmovements/"+materialmovement.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Materialmovement emp1 = materialmovementdao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> materialmovement Does Not Existed";
        if(errors=="") materialmovementdao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/materialmovements/"+id);
        response.put("errors",errors);
        return response;
    }
}
