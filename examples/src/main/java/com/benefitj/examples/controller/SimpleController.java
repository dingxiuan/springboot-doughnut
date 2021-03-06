package com.benefitj.examples.controller;

import com.alibaba.fastjson.JSON;
import com.benefitj.event.EventBusPoster;
import com.benefitj.event.RawEvent;
import com.benefitj.examples.vo.MultipartForm;
import com.benefitj.spring.ServletUtils;
import com.benefitj.spring.aop.AopIgnore;
import com.benefitj.spring.aop.web.AopWebPointCut;
import com.benefitj.spring.mvc.page.PageableRequest;
import com.benefitj.spring.mvc.request.PageBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AopWebPointCut
@RestController
@RequestMapping("/simple")
public class SimpleController {

  @Autowired
  private EventBusPoster poster;

  @GetMapping
  public ResponseEntity<?> get(String id) {
    poster.postSync(RawEvent.of(id));
    System.err.println("请求信息: " + JSON.toJSONString(ServletUtils.getRequestInfo()));
    return ResponseEntity.ok("id ==>: " + id);
  }

  @AopIgnore
  @GetMapping("/notPrint")
  public ResponseEntity<?> notPrint(String id) {
    return ResponseEntity.ok("id ==>: " + id);
  }


  @GetMapping("/form")
  public ResponseEntity<?> form(/*@GetBody*/ String id1, MultipartForm form, String id2) {
    System.err.println("id1 ==>: " + id1);
    System.err.println("id2 ==>: " + id2);
    return ResponseEntity.ok("form ==>: " + JSON.toJSONString(form));
  }

  @GetMapping("/page")
  public ResponseEntity<?> page(String id1, @PageBody PageableRequest<MultipartForm> form, String id2) {
    System.err.println("id1 ==>: " + id1);
    System.err.println("id2 ==>: " + id2);
    return ResponseEntity.ok("form ==>: " + JSON.toJSONString(form));
  }

}
