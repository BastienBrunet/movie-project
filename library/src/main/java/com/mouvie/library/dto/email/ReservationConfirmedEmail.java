package com.mouvie.library.dto.email;

import com.mouvie.library.model.Reservation;
import com.mouvie.library.tools.helper.DateHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReservationConfirmedEmail extends EmailTemplate {

    // NB : If you got the same name as parent element the child will always override the parent
    private final String BASE_SUBJECT = "Confirmation de réservation";

    private final String BASE_BODY ="Bonjour,<br>" +
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
        this.subject = BASE_SUBJECT;
        this.body = String.format(BASE_BODY,
                    reservation.getSceance().getMovie().getName(),
                    DateHelper.formatDate(reservation.getSceance().getDate()),
                    reservation.getSceance().getRoom().getCinema().getName());
    }

}
