package org.loja.model;

public class Result {
  Boolean erro;

  String entity;

  String action;

  Integer id;

  String message;

  StackTraceElement[] stackTrace;

  public Result(Boolean erro, String entity, String action, Integer id, String message, StackTraceElement[] stackTrace) {
    this.erro = erro;
    this.entity = entity;
    this.action = action;
    this.id = id;
    this.message = message;
    this.stackTrace = stackTrace;
  }

  public Boolean getErro() {
    return erro;
  }

  public void setErro(Boolean erro) {
    this.erro = erro;
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public StackTraceElement[] getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(StackTraceElement[] stackTrace) {
    this.stackTrace = stackTrace;
  }
}
