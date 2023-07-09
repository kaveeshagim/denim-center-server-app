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
        Materialmovement mat = materialmovementdao.findAllById(id);
        if(mat==null)
            errors = errors+"<br> materialmovement Does Not Existed";
        if(errors=="") materialmovementdao.delete(mat);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/materialmovements/"+id);
        response.put("errors",errors);
        return response;
    }
}
