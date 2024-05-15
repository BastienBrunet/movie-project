package com.mouvie.auth.config.appcontext;

import com.mouvie.library.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppContext {

    private User currentUser;
    private boolean isHalJson;
}
