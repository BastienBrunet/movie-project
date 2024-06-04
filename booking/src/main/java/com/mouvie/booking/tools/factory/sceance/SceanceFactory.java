package com.mouvie.booking.tools.factory.sceance;

import com.mouvie.booking.dto.model.sceances.SceanceInputDto;
import com.mouvie.library.model.Movie;
import com.mouvie.library.model.Room;
import com.mouvie.library.model.Sceance;

public class SceanceFactory {

    public static Sceance createSceance(SceanceInputDto inputDto, Room room, Movie movie){
        return new Sceance()
                .setMovie(movie)
                .setRoom(room)
                .setDate(inputDto.getDate().toInstant());
    }

    public static Sceance updateSceance(Sceance sceance, SceanceInputDto inputDto, Movie movie){
        return sceance
                .setMovie(movie)
                .setDate(inputDto.getDate().toInstant());
    }
}
