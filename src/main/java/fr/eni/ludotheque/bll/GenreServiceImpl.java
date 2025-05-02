package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.dal.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}
