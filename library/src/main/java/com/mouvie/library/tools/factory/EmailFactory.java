package com.mouvie.library.tools.factory;

import com.mouvie.library.dto.email.EmailTemplate;
import com.mouvie.library.dto.email.NewSceanceEmail;
import com.mouvie.library.dto.email.ReservationConfirmedEmail;
import com.mouvie.library.model.Reservation;
import com.mouvie.library.model.Sceance;
import com.mouvie.library.model.User;

import java.util.List;

public class EmailFactory {

    public static List<EmailTemplate> buildReservationConfirmedEmail(String to, Reservation reservation){
        return List.of(new ReservationConfirmedEmail(to, reservation));
    }

    public static List<NewSceanceEmail> buildNewSceanceEmail(List<User> users, Sceance sceance){
        return users.stream().map((u) -> new NewSceanceEmail(u.getUsername(), sceance)).toList();
    }
}
