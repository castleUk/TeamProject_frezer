package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TokenDto;
import com.example.demo.service.JwtService;
import com.example.demo.util.RefreshApiResponseMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class RefreshController {
  
  private final JwtService jwtService;


  @PostMapping("/refresh")
  public Map<String, String> validateRefreshToken(@RequestBody TokenDto tokenDto){

      log.info("refresh controller 실행");
      log.info("tokenDto" + tokenDto);
      Map<String, String> map = jwtService.validateRefreshToken(tokenDto.getRefreshToken());
      log.info("맵" + map);
      if(map.get("status").equals("402")){
          log.info("RefreshController - Refresh Token이 만료.");
          return map;
      }

      log.info("RefreshController - Refresh Token이 유효.");
      return map;

  }

}
