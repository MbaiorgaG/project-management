package com.example.projectmanagement.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public class DataTableUtil {

    public static Pageable getPageable(DataTablesInput input){
        if(input.getLength() < 0){
            return PageRequest.of(input.getStart(), Integer.MAX_VALUE);
        }
        return PageRequest.of(input.getStart()/input.getLength(), input.getLength());
    }
}
