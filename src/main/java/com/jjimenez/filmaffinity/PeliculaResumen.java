package com.jjimenez.filmaffinity;

public class PeliculaResumen {

    private Long id;
    private Integer nota;

    PeliculaResumen() {
    }

    PeliculaResumen(Long id, Integer nota) {
        this.id = id;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
