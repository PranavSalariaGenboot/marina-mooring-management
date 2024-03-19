package com.marinamooringmanagement.response;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Data
@Builder
public class BasicRestResponse implements Serializable {

    private String message;
    private int status;
    private List<String> errorList;
    private Timestamp time;
    private Object content;

    public BasicRestResponse(final int status, final String message, final Timestamp time) {
        super();
        this.status = status;
        this.message = message;
        this.time =  time;
    }
}
