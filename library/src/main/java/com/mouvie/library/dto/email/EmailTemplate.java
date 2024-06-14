package com.mouvie.library.dto.email;

import lombok.Data;

@Data
public abstract class EmailTemplate {

    protected String to;

    protected String subject;

    protected String body;

}