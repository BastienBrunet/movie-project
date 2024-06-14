package com.mouvie.library.dto.email;

import com.mouvie.library.model.Reservation;
import com.mouvie.library.tools.helper.DateHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReservationConfirmedEmail extends EmailTemplate {


    private final String SUBJECT = "Confirmation de réservation";

    private final String BODY ="Bonjour,<br>" +
            "<br>" +
            "Votre réservation est confirmée : <br>" +
            "Film : %s <br>" +
            "Date : %s<br>" +
            "Cinéma : %s<br>" +
            "<br>" +
            "Cordialement,<br>" +
            "<br>" +
            "Ceci est un message automatique, merci de ne pas y répondre.";

    public ReservationConfirmedEmail(String to, Reservation reservation){
        this.to = to;
        this.subject = SUBJECT;
        this.body = String.format(BODY,
                    reservation.getSceance().getMovie().getName(),
                    DateHelper.formatDate(reservation.getSceance().getDate()),
                    reservation.getSceance().getRoom().getCinema().getName());
    }

}
