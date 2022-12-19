package com.example.demo.controller;

import com.example.demo.dto.ChangePasswordRequestDto;
import com.example.demo.dto.MemberRequestDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/me")
  public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
    MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
    System.out.println(myInfoBySecurity.getNickname());
    return ResponseEntity.ok((myInfoBySecurity));
    // return ResponseEntity.ok(memberService.getMyInfoBySecurity());
  }

  @PostMapping("/nickname")
  public ResponseEntity<MemberResponseDto> setMemberNickname(
    @RequestBody MemberRequestDto request
  ) {
    return ResponseEntity.ok(
      memberService.changeMemberNickname(
        request.getEmail(),
        request.getNickname()
      )
    );
  }

  @PostMapping("/password")
  public ResponseEntity<MemberResponseDto> setMemberPassword(
    @RequestBody ChangePasswordRequestDto request
  ) {
    return ResponseEntity.ok(
      memberService.changeMemberPassword(
        request.getEmail(),
        request.getExPassword(),
        request.getNewPassword()
      )
    );
  }
}
