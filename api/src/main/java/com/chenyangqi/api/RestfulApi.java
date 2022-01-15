package com.chenyangqi.api;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * get post put delete请求示例
 */
@RestController
public class RestfulApi {

    private Map<Integer, Map<String, Object>> mDataMap;

    public RestfulApi() {
        this.mDataMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("name", "name" + 1);
            mDataMap.put(i, data);
        }
    }

    /**
     * get请求
     */
    @GetMapping("objects/{id}")
    public Map<String, Object> getData(@PathVariable Integer id) {
        return mDataMap.get(id);
    }

    /**
     * delete请求
     */
    @DeleteMapping("objects/{id}")
    public String deleteData(@PathVariable Integer id) {
        mDataMap.remove(id);
        return "delete success";
    }

    /**
     * post请求，json格式入参
     */
    @PostMapping("/objects")
    public String postData(@RequestBody Map<String, Object> data) {
        Integer[] idArray = mDataMap.keySet().toArray(new Integer[0]);
        Arrays.sort(idArray);
        int nextId = idArray[idArray.length - 1] + 1;
        mDataMap.put(nextId, data);
        return "post success";
    }

    /**
     * put请求
     * @param data
     * @return
     */
    @PutMapping("objects")
    public String putData(@RequestBody Map<String, Object> data) {
        Integer id = Integer.valueOf(String.valueOf(data.get("id")));
        Map<String, Object> containerData = mDataMap.get(id);
        if (containerData == null) {
            Integer[] idArray = mDataMap.keySet().toArray(new Integer[0]);
            Arrays.sort(idArray);
            int nextId = idArray[idArray.length - 1] + 1;
            mDataMap.put(nextId, data);
        } else {
            mDataMap.put(id, data);
        }
        return "put success";
    }
}
