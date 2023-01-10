package com.example.cliente.controller;

import com.example.cliente.entity.BaseResponse;

public class BaseController {
	
	
	public BaseResponse errorBase = new BaseResponse();
	
	public BaseController() {
		
		errorBase.message = "Erro inesperado";
		errorBase.statusCode = 500;

	}}
