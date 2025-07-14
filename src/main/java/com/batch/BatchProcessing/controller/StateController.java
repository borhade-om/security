package com.batch.BatchProcessing.controller;

import com.batch.BatchProcessing.dto.StateDto;
import com.batch.BatchProcessing.service.StateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping("/states")
    public String stateInsert(@RequestBody StateDto stateDto){
        return stateService.insertState(stateDto);
    }

    @GetMapping("/states")
    public List<StateDto> findAllState(){
        return stateService.findAllState();
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrf(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }




}
