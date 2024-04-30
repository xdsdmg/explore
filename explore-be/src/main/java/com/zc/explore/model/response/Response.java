package com.zc.explore.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.zc.explore.model.exception.EmailDupRegException;

public class Response<T extends ResultIF> extends ResponseEntity<T> {
  private HttpStatusCode httpStatusCode;

  public static Response<ResultSingle> create(Object data, Exception e) {
    if (e == null) {
      return new Response<ResultSingle>(new ResultSingle(0, "request successed", data), HttpStatus.OK);
    }

    if (e instanceof EmailDupRegException) {
      EmailDupRegException e_ = (EmailDupRegException) e;
      return new Response<ResultSingle>(new ResultSingle(e_.getCode(), e_.getMessage(), null), HttpStatus.BAD_REQUEST);
    } else {
      return new Response<ResultSingle>(new ResultSingle(1, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Response(T body, HttpStatusCode httpStatusCode) {
    super(body, httpStatusCode);
  }

  public HttpStatusCode getHttpStatusCode() {
    return this.httpStatusCode;
  }
}
