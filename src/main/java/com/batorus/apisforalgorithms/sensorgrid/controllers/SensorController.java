package com.batorus.apisforalgorithms.sensorgrid.controllers;

import com.batorus.apisforalgorithms.sensorgrid.dto.ListOfIntegerLists;
import com.batorus.apisforalgorithms.sensorgrid.model.Node;
import com.batorus.apisforalgorithms.sensorgrid.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SensorController {

    @Autowired
    SensorsService sensorsService;

    @PostMapping(path="/sensors/findfieldpath", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                                produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findPathAction(@RequestBody ListOfIntegerLists listOfIntegers) {

        Integer[][] listInput = listOfIntegers.getIntegerList();

        Node node = sensorsService.findShortestDistance(listInput);
        sensorsService.setPath(node);
       return new ResponseEntity<>(sensorsService.getPath(), HttpStatus.OK);
    }


    @GetMapping(path = "/sensors/getfieldpath", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFieldPathAction() {

        List<Node> theNodes = new ArrayList<>();
        theNodes.addAll(sensorsService.getPath());

        return new ResponseEntity<>(theNodes, HttpStatus.OK);
    }
}
