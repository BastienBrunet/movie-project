package com.mouvie.library.tools.constants;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class EmailConstants {


    @Value("${email.noreply}")
    public String noReply;
}
