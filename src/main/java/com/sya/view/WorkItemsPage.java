package com.sya.view;

import com.sya.model.Work;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WorkItemsPage {

    private List<WorkInfo> workList;

    public void setWorkList(List<Work> workList) {
        this.workList = workList
                .stream()
                .map(WorkInfo::new)
                .collect(Collectors.toList());
    }
}
