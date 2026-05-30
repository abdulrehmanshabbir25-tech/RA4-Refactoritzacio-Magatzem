/**
 * CODI REFACTORITZAT - COMMIT 3
 * Patró aplicat: Guard Clauses (Clàusules de Guarda).
 * S'aïllen els casos especials al principi del bucle per eliminar niuaments.
 */
class Magatzem {
    Article[] articles;

    public static final int MAX_QUALITAT = 50;
    public static final int QUALITAT_MINIMA = 0;
    public static final int LIMIT_DIES_URGENT = 11;
    public static final int LIMIT_DIES_CRITIC = 6;

    public Magatzem(Article[] articles) {
        this.articles = articles;
    }

    public void actualitzarEstat() {
        for (int i = 0; i < articles.length; i++) {
            
            // PATRÓ: Clàusula de Guarda (Guard Clause)
            // Si és el Martell de Thor, no canvia mai de qualitat ni de dies.
            // Passem directament al següent article del magatzem, evitant comprovar la resta de 'ifs'.
            if (articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                continue; 
            }

            // Flux principal (ja lliure de la condició del Martell de Thor)
            if (!articles[i].nom.equals("Formatge Gidurat")
                    && !articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                if (articles[i].qualitat > QUALITAT_MINIMA) {
                    articles[i].qualitat = articles[i].qualitat - 1;
                }
            } else {
                if (articles[i].qualitat < MAX_QUALITAT) {
                    articles[i].qualitat = articles[i].qualitat + 1;

                    if (articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                        if (articles[i].diesPerVendre < LIMIT_DIES_URGENT) {
                            if (articles[i].qualitat < MAX_QUALITAT) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }

                        if (articles[i].diesPerVendre < LIMIT_DIES_CRITIC) {
                            if (articles[i].qualitat < MAX_QUALITAT) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }
                    }
                }
            }

            // Reduïm els dies per vendre (ja sabem segur que no és el Martell)
            articles[i].diesPerVendre = articles[i].diesPerVendre - 1;

            if (articles[i].diesPerVendre < 0) {
                if (!articles[i].nom.equals("Formatge Gidurat")) {
                    if (!articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                        if (articles[i].qualitat > QUALITAT_MINIMA) {
                            articles[i].qualitat = articles[i].qualitat - 1;
                        }
                    } else {
                        articles[i].qualitat = articles[i].qualitat - articles[i].qualitat;
                    }
                } else {
                    if (articles[i].qualitat < MAX_QUALITAT) {
                        articles[i].qualitat = articles[i].qualitat + 1;
                    }
                }
            }
        }
    }
}