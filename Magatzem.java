/**
 * CODI LLEGAT (Legacy Code): Conté l'algorisme original complex amb un niuament elevat d'if/else.
 * Es puja completament intacte sense refactoritzar per servir com a punt de partida a Git.
 */
class Magatzem {
    Article[] articles;

    public Magatzem(Article[] articles) {
        this.articles = articles;
    }

    public void actualitzarEstat() {
        for (int i = 0; i < articles.length; i++) {
            if (!articles[i].nom.equals("Formatge Gidurat")
                    && !articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                if (articles[i].qualitat > 0) {
                    if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                        articles[i].qualitat = articles[i].qualitat - 1;
                    }
                }
            } else {
                if (articles[i].qualitat < 50) {
                    articles[i].qualitat = articles[i].qualitat + 1;

                    if (articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                        if (articles[i].diesPerVendre < 11) {
                            if (articles[i].qualitat < 50) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }

                        if (articles[i].diesPerVendre < 6) {
                            if (articles[i].qualitat < 50) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }
                    }
                }
            }

            if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                articles[i].diesPerVendre = articles[i].diesPerVendre - 1;
            }

            if (articles[i].diesPerVendre < 0) {
                if (!articles[i].nom.equals("Formatge Gidurat")) {
                    if (!articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                        if (articles[i].qualitat > 0) {
                            if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                                articles[i].qualitat = articles[i].qualitat - 1;
                            }
                        }
                    } else {
                        articles[i].qualitat = articles[i].qualitat - articles[i].qualitat;
                    }
                } else {
                    if (articles[i].qualitat < 50) {
                        articles[i].qualitat = articles[i].qualitat + 1;
                    }
                }
            }
        }
    }
}