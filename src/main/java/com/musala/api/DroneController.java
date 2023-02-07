package com.musala.api;

import com.musala.api.base.BaseApi;
import com.musala.dto.DroneDto;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("drone")
public interface DroneController extends BaseApi<DroneDto> {

}
