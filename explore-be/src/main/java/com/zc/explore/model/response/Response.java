package com.zc.explore.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.zc.explore.model.base.ResultList;
import com.zc.explore.model.exception.ExceptionIF;

public class Response<T extends ResultIF> extends ResponseEntity<T> {
  private HttpStatusCode httpStatusCode;

  public static Response<ResultSingle> createSingleResponse(Object data, Exception e) {
    if (e == null) {
      ResultSingle r = new ResultSingle(0, "Request successed", data);
      return new Response<ResultSingle>(r, HttpStatus.OK);
    }

    if (e instanceof ExceptionIF) {
      ExceptionIF e_ = (ExceptionIF) e;
      ResultSingle r = new ResultSingle(e_.getCode(), e_.getMessage(), null);
      return new Response<ResultSingle>(r, HttpStatus.BAD_REQUEST);
    } else {
      ResultSingle r = new ResultSingle(1, e.getMessage(), null);
      return new Response<ResultSingle>(r, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static Response<ResultArray> createArrayResponse(ResultList data, Exception e) {
    if (e == null) {
      ResultArray r = new ResultArray(0, "Request successed", data.getTotal(), data.getData());
      return new Response<ResultArray>(r, HttpStatus.OK);
    }

    if (e instanceof ExceptionIF) {
      ExceptionIF e_ = (ExceptionIF) e;
      ResultArray r = new ResultArray(e_.getCode(), e_.getMessage(), 0, null);
      return new Response<ResultArray>(r, HttpStatus.BAD_REQUEST);
    } else {
      ResultArray r = new ResultArray(1, e.getMessage(), 0, null);
      return new Response<ResultArray>(r, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Response(T body, HttpStatusCode httpStatusCode) {
    super(body, httpStatusCode);
  }

  public HttpStatusCode getHttpStatusCode() {
    return this.httpStatusCode;
  }
}
