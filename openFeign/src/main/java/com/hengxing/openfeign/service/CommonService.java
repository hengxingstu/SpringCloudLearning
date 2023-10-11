package com.hengxing.openfeign.service;

import com.hengxing.common.API.commonApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 10/11/2023 00:12:50
 */
@FeignClient(value = "provider01",fallback = FallBackService.class)
public interface CommonService extends commonApi {
}
