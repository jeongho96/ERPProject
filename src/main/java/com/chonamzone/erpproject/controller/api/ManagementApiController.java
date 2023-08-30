package com.chonamzone.erpproject.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chonamzone.erpproject.service.ManagementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManagementApiController {
	
	private final ManagementService managementService;
	
}
