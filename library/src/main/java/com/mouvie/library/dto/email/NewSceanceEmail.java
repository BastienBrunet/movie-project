package com.mouvie.library.dto.email;

import com.mouvie.library.model.Reservation;
import com.mouvie.library.model.Sceance;
import com.mouvie.library.tools.helper.DateHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewSceanceEmail extends EmailTemplate {

    private final String SUBJECT = "Nouvelle séance";

    private final String BODY ="Bonjour,<br>" +
            "<br>" +
            "Une nouvelle séance est disponible pour le film : %s.<br>" +
            "Date : %s<br>" +
            "Cinéma : %s<br>" +
            "<br>" +
            "Cordialement,<br>" +
            "<br>" +
            "Ceci est un message automatique, merci de ne pas y répondre.";

    public NewSceanceEmail(String to, Sceance sceance){
        this.to = to;
        this.subject = SUBJECT;
        this.body = String.format(BODY,
                sceance.getMovie().getName(),
                DateHelper.formatDate(sceance.getDate()),
                sceance.getRoom().getCinema().getName());
    }
}
