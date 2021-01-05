package com.sya.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pagination {

    @JsonProperty("totalpage")
    private Integer totalPage;

    @JsonProperty("pagenum")
    private Integer pageNum;

    @JsonProperty("pagesize")
    private Integer pageSize;
}
