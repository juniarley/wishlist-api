package com.wishlist.wishlistapi.api.exceptionhandler;


import lombok.Getter;

@Getter
public enum ProblemType {

    DADOS_INVALIDOS("/invalid-data", "Invalid Data"),
    ERRO_DE_SISTEMA("/system-error", "System error"),
    PARAMETRO_INVALIDO("/invalid-parameter", "Invalid Parameter"),
    MENSAGEM_INCOMPREENSIVEL("/incomprehensible-message", "Incomprehensible message"),
    RECURSO_NAO_ENCONTRADO("/resource-not-found", "Resource not found"),
    ERRO_NEGOCIO("/erro-business", "Business rule violation");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://wishlistapi.com" + path;
        this.title = title;
    }

}